package group.examdesign.service;

import group.examdesign.model.Profile;

import java.util.Optional;

public interface IProfileService extends ICrudService<Profile, Long> {
    void deleteByUser_Username(String username);
    Optional<Profile> findByUsername(String username);
}
