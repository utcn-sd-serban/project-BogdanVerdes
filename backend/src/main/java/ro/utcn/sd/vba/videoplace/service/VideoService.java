package ro.utcn.sd.vba.videoplace.service;


import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.vba.videoplace.dto.VideoDTO;
import ro.utcn.sd.vba.videoplace.entity.Video;
import ro.utcn.sd.vba.videoplace.entity.VideoTag;
import ro.utcn.sd.vba.videoplace.entity.Tag;
import ro.utcn.sd.vba.videoplace.event.CreateVideoEvent;
import ro.utcn.sd.vba.videoplace.repository.api.RepositoryFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final RepositoryFactory repositoryFactory;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public VideoDTO saveVideo(VideoDTO videoDTO) throws SQLException {

        Video video = new Video(videoDTO.getTitle(), videoDTO.getLink(), videoDTO.getUserId());
        String tags = videoDTO.getTags();
        VideoDTO vDTO = VideoDTO.ofEntity(repositoryFactory.createVideoRepository().save(video));
        String[] tagList = tags.split(",");
        for (String aTagList : tagList) {
            Tag tag = new Tag(aTagList);
            repositoryFactory.createTagRepository().save(tag);
            VideoTag videoTag = new VideoTag(tag.getId(), vDTO.getId());
            repositoryFactory.createVideoTagRepository().save(videoTag);
        }

        List<Integer> videoTags = repositoryFactory.createVideoTagRepository().findAll()
                .stream()
                .filter(qTag -> qTag.getIdVideo() == vDTO.getId())
                .map(VideoTag::getIdTag)
                .collect(Collectors.toList());

        vDTO.setTags(tags);
        applicationEventPublisher.publishEvent(new CreateVideoEvent(vDTO));
        return vDTO;
    }

    @Transactional
    public VideoDTO findById(int id) throws SQLException {
        Optional<Video> videoOptional = repositoryFactory.createVideoRepository().findById(id);
        if (videoOptional.isPresent()) {
            Video video = videoOptional.get();
            return VideoDTO.ofEntity(repositoryFactory.createVideoRepository().save(video));
        } else return new VideoDTO();
    }

    @Transactional
    public List<VideoDTO> findAllVideos() {
        List<Video> videos = repositoryFactory.createVideoRepository().findAll();
        List<Tag> tags = repositoryFactory.createTagRepository().findAll();
        List<VideoDTO> videoDTOList = new ArrayList<>();
        for (Video v : videos) {
            List<Tag> videoTags = repositoryFactory.createVideoTagRepository().findTagsByVideo(v, tags);
            StringBuilder tagString = new StringBuilder();
            for (Tag t : videoTags) {
                tagString.append(t.getName()).append(",");
            }
            VideoDTO vDTO = VideoDTO.ofEntity(v);
            String newTag = tagString.toString().substring(0, tagString.toString().length() - 1);
            vDTO.setTags(newTag);
            videoDTOList.add(vDTO);
        }
        return videoDTOList;
    }

    @Transactional
    public List<VideoDTO> findByTitle(String title) {
        List<Video> videos = repositoryFactory.createVideoRepository().findAll();
        List<Tag> tags = repositoryFactory.createTagRepository().findAll();
        List<VideoDTO> videoDTOList = new ArrayList<>();
        for (Video v : videos) {
            if (v.getTitle().contains(title)) {
                List<Tag> videoTags = repositoryFactory.createVideoTagRepository().findTagsByVideo(v, tags);

                videoTags.forEach(t -> System.out.print(t.getName() + ","));
                System.out.println("");
                StringBuilder tagString = new StringBuilder();
                for (Tag t : videoTags) {
                    tagString.append(t.getName()).append(",");
                }
                VideoDTO vDTO = VideoDTO.ofEntity(v);
                String newTag = tagString.toString().substring(0, tagString.toString().length() - 1);
                vDTO.setTags(newTag);
                videoDTOList.add(vDTO);
            }
        }
        return videoDTOList;
    }
}

