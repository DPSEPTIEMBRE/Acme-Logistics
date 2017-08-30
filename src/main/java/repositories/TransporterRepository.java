
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Transporter;

@Repository
public interface TransporterRepository extends JpaRepository<Transporter, Integer> {

}
