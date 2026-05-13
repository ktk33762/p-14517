package com.back.domain.post.post;

import com.back.domain.post.post.dto.Post;
import com.back.domain.post.post.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class PostServiceTest {
    @Autowired
    private PostService postService;

    @Test
    @DisplayName("게시물 다건 조회")
    void t1 () {
        List<Post> posts = postService.findAll();

        assertThat(posts).hasSize(2);
    }
}
