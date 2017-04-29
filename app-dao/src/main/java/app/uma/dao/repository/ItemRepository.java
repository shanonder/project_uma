package app.uma.dao.repository;

import org.springframework.data.repository.CrudRepository;

import app.uma.dao.entity.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
