package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.RegistrationKey;

public interface RegistrationKeyRepository extends JpaRepository <RegistrationKey, String>{
	RegistrationKey findOneByRegistrationKeyAndEmail(String registrationKey, String email);
}
