package tech.fcscode.ushort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.fcscode.ushort.model.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url,Long> {

}
