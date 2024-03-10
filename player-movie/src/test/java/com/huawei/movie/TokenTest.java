package com.huawei.movie;

import com.player.common.entity.ResultEntity;
import com.player.common.utils.JwtToken;
import com.player.common.entity.UserEntity;
import com.player.movie.PlayerMovieApplication;
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

    private JwtToken jwtToken = new JwtToken();

    @Test
    public void parseToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTYyMDM4NzEsInN1YiI6IntcImF2YXRlclwiOlwiL3N0YXRpYy91c2VyL2F2YXRlci_lkLTlsL3lkLTnqbcuanBnXCIsXCJiaXJ0aGRheVwiOlwiMTk5MC0xMC04XCIsXCJjcmVhdGVEYXRlXCI6MTU2NjE0NzU2ODAwMCxcImVtYWlsXCI6XCIyNzUwMTg3MjNAcXEuY29tXCIsXCJyb2xlXCI6XCJwdWJsaWNcIixcInNleFwiOlwi55S3XCIsXCJ0ZWxlcGhvbmVcIjpcIjE1MzAyNjg2OTQ3XCIsXCJ1cGRhdGVEYXRlXCI6MTU2NjE0NzU3MjAwMCxcInVzZXJJZFwiOlwi5ZC05bC95ZC056m3XCIsXCJ1c2VybmFtZVwiOlwi5ZC05bC95ZC056m3XCJ9IiwiZXhwIjoxNjE4Nzk1ODcxfQ.FB9Ru3j1Y2_VUUy-eVn1e9DGbj1bZhZxnRfWI3QhVl4";
        UserEntity userEntity = jwtToken.parserToken(token, UserEntity.class);
        System.out.println(userEntity.toString());
    }
}
