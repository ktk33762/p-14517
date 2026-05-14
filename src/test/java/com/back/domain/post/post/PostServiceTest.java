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

    @Test
    @DisplayName("게시물 단건 조회")
    void t2 () {
        Post post = postService.findById(1);

        assertThat(post.getTitle()).isEqualTo("제목 1");
        assertThat(post.getContent()).isEqualTo("내용 1");
    }
    @Test
    @DisplayName("게시물 생성")
    void t3 () {
        // when: 게시글 작성
        int id = postService.create("제목 3", "내용 3");

        // then: 해당 id의 게시글 불러오기
        Post post = postService.findById(id);

        assertThat(post.getTitle()).isEqualTo("제목 3");
        assertThat(post.getContent()).isEqualTo("내용 3");
    }

    @Test
    @DisplayName("게시물 생성")
    void t4 () {
        // when: 게시글 작성
        postService.createV2("제목 3", "내용 3");
        // then: 해당 id의 게시글 불러오기
        int id = postService.getLastInsertId();
        Post post = postService.findById(id);

        assertThat(post.getTitle()).isEqualTo("제목 3");
        assertThat(post.getContent()).isEqualTo("내용 3");
    }

    @Test
    @DisplayName("게시물 삭제")
    void t5 () {
        // when: 게시글 작성
        postService.deleteById(1);

        List<Post> posts = postService.findAll();

        assertThat(posts).hasSize(1);
    }
}
