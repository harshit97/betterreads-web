package io.harshit.betterreadsweb.userbook;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UserBooksRepository extends CassandraRepository<UserBooks, UserBooksPrimaryKey> {
    
}
