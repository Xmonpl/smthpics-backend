package org.eu.xmon.smthpicsrest.controler;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.google.gson.Gson;
import com.typesafe.config.Config;
import com.typesafe.config.impl.ConfigImpl;
import io.jooby.Cookie;
import io.jooby.Jooby;
import io.jooby.MediaType;
import io.jooby.annotations.PUT;
import io.jooby.annotations.Path;
import io.jooby.annotations.QueryParam;
import org.eu.xmon.smthpicsrest.database.DbConnect;
import org.eu.xmon.smthpicsrest.object.*;
import org.eu.xmon.smthpicsrest.response.StandardResponse;
import org.eu.xmon.smthpicsrest.response.StatusResponse;

import java.util.List;
import java.util.UUID;


public class UserControler extends Jooby {
    {
        path("/api/v1/user", () ->{

            post("/", ctx -> {
                ctx.setResponseType(MediaType.json);
                if (ctx.query("login").isMissing() || ctx.query("password").isMissing() ){
                    ctx.setResponseCode(400);
                    return new Gson().toJson(StandardResponse.builder().status(StatusResponse.Error).message("Bad Request - error 400").build());
                }
                final User user = DbConnect.getDatabase().sql("SELECT * FROM users WHERE email = ? OR nickname = ?", ctx.query("login").value(), ctx.query("login").value()).first(User.class);
                if (user == null){
                    return new Gson().toJson(StandardResponse.builder().status(StatusResponse.Error).message("The email or nickname is incorrect or the account has not been activated").build());
                }
                final BCrypt.Result result = BCrypt.verifyer().verify(ctx.query("password").value().toCharArray(), user.password);
                if (!result.verified){
                    //@TODO action here
                    return new Gson().toJson(StandardResponse.builder().status(StatusResponse.Error).message("The email or password is incorrect or the account has not been activated").build());
                }
                if (!user.actived){
                    return new Gson().toJson(StandardResponse.builder().status(StatusResponse.Error).message(user.reasonUnactived).build());
                }
                ctx.setResponseCookie(new Cookie("token", BCrypt.withDefaults().hashToString(4, (user.uuid + "-" + ctx.getRemoteAddress()).toCharArray())).setPath("/").setMaxAge(36000));
                ctx.setResponseCookie(new Cookie("uuid", String.valueOf(user.uuid)).setPath("/").setMaxAge(36000));
                return new Gson().toJson(StandardResponse.builder().status(StatusResponse.OK).message("Login Succesfully").build());
            });

            put("/", ctx ->{
                ctx.setResponseType(MediaType.json);
                if (ctx.query("nickname").isMissing() || ctx.query("email").isMissing() || ctx.query("password").isMissing()){
                    ctx.setResponseCode(400);
                    return new Gson().toJson(StandardResponse.builder().status(StatusResponse.Error).message("Bad Request - error 400").build());
                }
                if (DbConnect.getDatabase().sql("SELECT email from users WHERE email = ?;", ctx.query("email").value()).first(String.class) != null) {
                    return new Gson().toJson(StandardResponse.builder().status(StatusResponse.Error).message("Email is already exist! An account already exists on this email address").build());
                }
                if (DbConnect.getDatabase().sql("SELECT nickname from users WHERE nickname = ?;", ctx.query("nickname").value()).first(String.class) != null) {
                    return new Gson().toJson(StandardResponse.builder().status(StatusResponse.Error).message("Nickname is already exist! An account already exists on this nickname").build());
                }
                User user = User.builder()
                        .actived(false)
                        .createdTime(System.currentTimeMillis())
                        .email(ctx.query("email").value())
                        .nickname(ctx.query("nickname").value())
                        .role(Role.USER)
                        .reasonUnactived("Email is not confirmed")
                        .uploads(0L)
                        .uploadsSize(0L)
                        .token(UUID.randomUUID())
                        .uuid(UUID.randomUUID())
                        .password(BCrypt.withDefaults().hashToString(8, ctx.query("password").value().toCharArray()))
                        .build();
                UserSetting userSetting = UserSetting.builder()
                        .uuid(user.uuid)
                        .embed(
                                Embed.builder()
                                        .build()
                        )
                        .uploadSetting(
                                UploadSetting.builder()
                                        .domains(List.of(Domain.builder().domain("smth.pics").build()))
                                        .build()
                        )
                        .profile(
                                Profile.builder()
                                        .avatar(String.format("https://avatars.dicebear.com/api/identicon/%s.svg", ctx.query("nickname").value()))
                                        .commentType(CommentType.PUBLIC)
                                        .country(ctx.header("CF-IPCountry").value("EN"))
                                        .dateFormat("DD/MM/YYYY")
                                        .isPublic(true)
                                        .timeZone(ctx.header("CF-IPCountry").value("EN"))
                                        .language(ctx.header("CF-IPCountry").value("EN"))
                                        .build()
                        )
                        .build();
                DbConnect.getDatabase().insert(user);
                DbConnect.getDatabase().insert(userSetting);
                //@TODO send verification code to email
                return new Gson().toJson(StandardResponse.builder().status(StatusResponse.OK)
                        .message("The account has been created successfully")
                                .data(new Gson().toJsonTree(user))
                        .build());

            });

        });
    }
}
