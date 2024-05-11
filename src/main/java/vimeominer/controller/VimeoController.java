package vimeominer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import imports.model.Caption;
import imports.model.Channel;
import imports.model.Comment;
import imports.model.User;
import imports.model.Video;
import vimeominer.model.*;
import vimeominer.videoservice.*;
import vimeominer.vimeoservice.*;

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

    @Autowired
    ChannelService videoChannelService;

    @Autowired
    VideoService videoVideoService;

    @Autowired
    UserService videoUserService;

    @Autowired
    CommentService videoCommentService;

    @Autowired
    CaptionService videoCaptionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{vimeoChannelId}")
    public Channel create(@PathVariable String vimeoChannelId,
                      @RequestParam(name = "maxVideos", required = false) Integer maxVideos,
                      @RequestParam(name = "maxComments", required = false) Integer maxComments) {
        long numMaxVideos = 10L, numMaxComments = 10L;
        if (maxVideos!=null) {
            numMaxVideos = maxVideos.longValue();
        }
        if (maxComments!=null) {
            numMaxComments = maxComments.longValue();
        }
        VimeoChannel channel = channelService.getVimeoChannel(vimeoChannelId);
        System.out.println(channel);
        Channel canal = videoChannelService.creaCanal(channel.getId(), channel.getName(), channel.getDescription(),
                channel.getCreatedTime());
        VimeoVideoList videoList = videoService.getVimeoVideoList(vimeoChannelId, null);
        List<VimeoVideo> videos = new ArrayList<>(videoList.getVideos());
        for(int i=2; i<=videoList.getNumPags() && videos.size() <=numMaxVideos; i++) {
            videos.addAll(videoService.getVimeoVideoList(vimeoChannelId, i).getVideos());
        }
        for(VimeoVideo video:videos.stream().limit(numMaxVideos).toList()) {
            Video v = videoVideoService.creaCanal(canal.getId(), video.getId(), video.getName(),
                    video.getDescription(), video.getReleasedTime());
            VimeoTexttrackList captionList = captionService.getVimeoTexttrackList(video.getId(),
                    null);
            video.addTexttracks(captionList.getTexttracks());
            for(int i=2; i<=captionList.getNumPags(); i++) {
                video.addTexttracks(captionService.getVimeoTexttrackList(video.getId(), i)
                                                  .getTexttracks());
            }
            for(VimeoTexttrack caption:video.getTexttracks()) {
                Caption subtitulo = videoCaptionService.creaSubtitulo(canal.getId(), v.getId(), caption.getId(),
                        caption.getName(), caption.getLanguage());
            }
            VimeoCommentList commentList = commentService.getVimeoCommentList(video.getId(),
                    null);
            List<VimeoComment> comments = new ArrayList<>(commentList.getComments());
            for(int i=2; i<=commentList.getNumPags() && comments.size()<=numMaxComments; i++) {
                comments.addAll(commentService.getVimeoCommentList(video.getId(), i).getComments());
            }
            for(VimeoComment comment:comments.stream().limit(numMaxComments).toList()) {
                VimeoUser user = userService.getVimeoUser(comment.getUser().getId());
                VimeoPictureList pictureList = pictureLinkService.getVimeoPictureList(user.getId());
                String pictureLink = !pictureList.getPictures().isEmpty() ?
                        pictureList.getPictures().get(0).getLink() : null;
                User usuario = videoUserService.creaUsuario(user.getName(), user.getUserLink(),
                        pictureLink);
                Comment comentario = videoCommentService.creaComentario(canal.getId(), v.getId(), comment.getId(),
                        comment.getText(), comment.getCreatedOn(), usuario);
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
        return canal;
    }

}
