package com.example.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSportsMember is a Querydsl query type for SportsMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSportsMember extends EntityPathBase<SportsMember> {

    private static final long serialVersionUID = 1288807360L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSportsMember sportsMember = new QSportsMember("sportsMember");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final QLocker locker;

    public final StringPath name = createString("name");

    public QSportsMember(String variable) {
        this(SportsMember.class, forVariable(variable), INITS);
    }

    public QSportsMember(Path<? extends SportsMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSportsMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSportsMember(PathMetadata metadata, PathInits inits) {
        this(SportsMember.class, metadata, inits);
    }

    public QSportsMember(Class<? extends SportsMember> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.locker = inits.isInitialized("locker") ? new QLocker(forProperty("locker"), inits.get("locker")) : null;
    }

}

