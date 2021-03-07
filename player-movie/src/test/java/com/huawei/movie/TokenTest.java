package com.huawei.movie;

import com.player.common.entity.ResultEntity;
import com.player.common.utils.JwtToken;
import com.player.common.entity.UserEntity;
import com.player.movie.PlayerMovieApplication;
import com.player.movie.mapper.MovieMapper;
import com.player.movie.service.IMovieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlayerMovieApplication.class)
public class TokenTest {
    @Autowired
    private IMovieService movieService;

    JwtToken jwtToken = new JwtToken();

    @Test
    public void createToken() {
        ResultEntity resultEntity = movieService.getUserData(null);
        UserEntity userEntity = (UserEntity) resultEntity.getData();
        String token = jwtToken.createToken(userEntity, 1000 * 60 * 60);
        System.out.println(token);
    }

    @Test
    public void parseToken() {
        String token = " eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MDk0Nzc4MTUsInN1YiI6IntcImF2YXRlclwiOlwiL2ltYWdlcy9hdmF0ZXIvdXNlci_liJ3mmZPlvq7oipIuanBnXCIsXCJiaXJ0aGRheVwiOlwiMTk5MC0xMC04XCIsXCJjcmVhdGVEYXRlXCI6XCIyMDIwLTAxLTExIDE4OjQ3OjAxLjBcIixcImVtYWlsXCI6XCIyNzUwMTg3MjNAcXEuY29tXCIsXCJyb2xlXCI6XCJwdWJsaWNcIixcInNleFwiOlwi5aWzXCIsXCJ0ZWxlcGhvbmVcIjpcIjE1MzAyNjg2OTQ3XCIsXCJ1cGRhdGVEYXRlXCI6XCIyMDIwLTAxLTExIDE4OjQ3OjAzLjBcIixcInVzZXJJZFwiOlwi5Yid5pmT5b6u6IqSXCIsXCJ1c2VybmFtZVwiOlwi5Yid5pmT5b6u6IqSXCJ9IiwiZXhwIjoxNjA5NTY0MjE1fQ.-Jw-5cdMUARbOoigQbcx9710rti6z83GF24jeVuHJRI";
        UserEntity userEntity = jwtToken.parserToken(token, UserEntity.class);
        System.out.println(userEntity.toString());
    }
}
