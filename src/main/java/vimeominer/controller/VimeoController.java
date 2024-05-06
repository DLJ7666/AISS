package vimeominer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vimeominer")
public class VimeoController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(String vimeoChannelId) {
        /*
        GET /channels/{vimeoChannelId}
        new Channel c = ^
        GET /channels/{vimeoChannelId}/videos
        for(VimeoVideo video:videos) {
            GET /channels/{vimeoChannelId}/videos/{video.getId()}
            new Video v = ^
            GET /channels/{vimeoChannelId}/videos/{video.getId()}/texttracks
            for(VimeoTexttrack texttrack:textracks) {
                GET /channels/{vimeoChannelId}/videos/{video.getId()}/texttracks/{texttrack.getId()}
                new Caption s = ^
                v.addCaption(s)
            }
            GET /channels/{vimeoChannelId}/videos/{video.getId()}/comments
            for(VimeoComment comment:comments) {
                GET /channels/{vimeoChannelId}/videos/{video.getId()}/comments/{comment.getId()}
                new Comment co = ^
                GET /users/{comment.getUser().getId()}
                new User u = ^
                v.addComment(co)
            }
            c.addVideo(v)
        }
        */
    }

}
