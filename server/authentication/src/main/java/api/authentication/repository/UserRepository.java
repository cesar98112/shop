package api.authentication.repository;

import api.authentication.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserModel,String> {

    public Optional<UserModel> findUserByUsername(String username);
}
