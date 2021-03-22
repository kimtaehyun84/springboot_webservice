package com.kimtaehyun84.springboot_webservice.domain.posts;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public  void loadPosts(){
        //given
        String title = "Test Title";
        String content = "Test Contents";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("taehyun.kim")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

    @Test
    public void registerBaseTimeEntity(){
        //given
        LocalDateTime now = LocalDateTime.of(2021,2,16,0,0,0);
        postsRepository.save(Posts.builder()
                           .title("title")
                           .content("content")
                           .author("author")
                           .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>>> createDate="+posts.getCreateDate()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreateDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }

}