package io.harshit.betterreadsweb.user;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;
import java.util.UUID;


/**
 * Model that represents the books read by a user.
 * Helps with showing user's recent books in the dashboard page.
 */
@Table(value = "books_by_user")
@Data
public class BooksByUser {

    @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String id;

    @PrimaryKeyColumn(name = "book_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    @CassandraType(type = Name.TEXT)
    private String bookId;

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    @CassandraType(type = Name.TIMEUUID)
    private UUID timeUuid;

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
    @CassandraType(type = Name.TEXT)
    private String readingStatus;

    @Column("book_name")
    @CassandraType(type = Name.TEXT)
    private String bookName;

    @Column("author_names")
    @CassandraType(type = Name.LIST, typeArguments = Name.TEXT)
    private List<String> authorNames;
    
    @Column("cover_ids")
    @CassandraType(type = Name.LIST, typeArguments = Name.TEXT)
    private List<String> coverIds;

    

    @Column("rating")
    @CassandraType(type = Name.INT)
    private int rating;

    @Transient
    private String coverUrl;


    public BooksByUser() {
        this.timeUuid = Uuids.timeBased();
    }

}
