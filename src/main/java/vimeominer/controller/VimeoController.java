package vimeominer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import videominer.controller.CommentController;
import videominer.model.Comment;
import videominer.model.User;
import vimeominer.model.*;
import vimeominer.service.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/vimeominer")
public class VimeoController {

    @Autowired
    VimeoChannelService channelService;

    @Autowired
    VimeoCommentService commentService;

    @Autowired
    VimeoPictureService pictureLinkService;

    @Autowired
    VimeoTexttrackService captionService;

    @Autowired
    VimeoUserService userService;

    @Autowired
    VimeoVideoService videoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(String vimeoChannelId) {
        VimeoChannel channel = channelService.getVimeoChannel(vimeoChannelId);
        VimeoVideoList videoList = videoService.getVimeoVideoList(vimeoChannelId, null);
        List<VimeoVideo> videos = new ArrayList<>(videoList.getVideos());
        for(int i=2; i<=videoList.getNumPags(); i++) {
            videos.addAll(videoService.getVimeoVideoList(vimeoChannelId, i).getVideos());
        }
        for(VimeoVideo video:videos) {
            VimeoTexttrackList captionList = captionService.getVimeoTexttrackList(video.getId(), null);
            video.addTexttracks(captionList.getTexttracks());
            for(int i=2; i<=captionList.getNumPags(); i++) {
                video.addTexttracks(captionService.getVimeoTexttrackList(video.getId(), i).getTexttracks());
            }
            VimeoCommentList commentList = commentService.getVimeoCommentList(video.getId(), null);
            List<VimeoComment> comments = new ArrayList<>(commentList.getComments());
            for(int i=2; i<=commentList.getNumPags(); i++) {
                comments.addAll(commentService.getVimeoCommentList(video.getId(), i).getComments());
            }
            for(VimeoComment comment:comments) {
                VimeoUser user = userService.getVimeoUser(comment.getUser().getId());
                VimeoPictureList pictureList = pictureLinkService.getVimeoPictureList(user.getId());
                String pictureLink = !pictureList.getPictures().isEmpty() ?
                        pictureList.getPictures().get(0).getLink():null;
                User u = new User(user.getName(), user.getUserLink(), pictureLink);
                Comment c = new Comment(comment.getText(), comment.getCreatedOn(), u);
            }
        }
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
