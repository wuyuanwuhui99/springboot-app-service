package com.player.music;

import com.player.common.entity.UserEntity;
import com.player.common.utils.JwtToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlayerMusicApplication.class)
public class TokenTest {

    @Test
    public void parseToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTYxNzQxMTYsInN1YiI6IntcInVzZXJJZFwiOlwi5ZC05bC95ZC056m3XCIsXCJjcmVhdGVEYXRlXCI6XCIyMDE5LTA4LTE4VDE2OjU5OjI4LjAwMCswMDAwXCIsXCJ1cGRhdGVEYXRlXCI6XCIyMDE5LTA4LTE4VDE2OjU5OjMyLjAwMCswMDAwXCIsXCJ1c2VybmFtZVwiOlwi5ZC05bC95ZC056m3XCIsXCJ0ZWxlcGhvbmVcIjpcIjE1MzAyNjg2OTQ3XCIsXCJlbWFpbFwiOlwiMjc1MDE4NzIzQHFxLmNvbVwiLFwiYXZhdGVyXCI6XCIvc3RhdGljL3VzZXIvYXZhdGVyL-WQtOWwveWQtOepty5qcGdcIixcImJpcnRoZGF5XCI6XCIxOTkwLTEwLThcIixcInNleFwiOlwi55S3XCIsXCJyb2xlXCI6XCJwdWJsaWNcIn0iLCJleHAiOjE2MTg3NjYxMTZ9.R8oA080xVLkXwAv89YxRhOcVYbMznwd5XwDT3nDFMvg";
        JwtToken jwtToken = new JwtToken();
        UserEntity userEntity = jwtToken.parserToken(token, UserEntity.class);
        System.out.println(userEntity.toString());
    }
}
