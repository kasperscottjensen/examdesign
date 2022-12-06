package group.examdesign.service;

import group.examdesign.model.Newsfeed;
import group.examdesign.model.Wishes;

import java.util.List;

public interface INewsfeedService extends ICrudService<Newsfeed,Long>{
    List<Newsfeed> findAllNewsfeedByProfile_Username(String username);
}
