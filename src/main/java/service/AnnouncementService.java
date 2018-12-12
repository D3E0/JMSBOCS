package service;

import entity.AnnouncementEntity;

import java.util.List;

public interface AnnouncementService extends BaseService<AnnouncementEntity> {
    List<AnnouncementEntity> selectCourseAnnouncement(int courseId);

}
