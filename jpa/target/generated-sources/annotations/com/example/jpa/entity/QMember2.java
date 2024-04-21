package com.example.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember2 is a Querydsl query type for Member2
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember2 extends EntityPathBase<Member2> {

    private static final long serialVersionUID = -923473999L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember2 member2 = new QMember2("member2");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QTeam2 team2;

    public final StringPath userName = createString("userName");

    public QMember2(String variable) {
        this(Member2.class, forVariable(variable), INITS);
    }

    public QMember2(Path<? extends Member2> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember2(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember2(PathMetadata metadata, PathInits inits) {
        this(Member2.class, metadata, inits);
    }

    public QMember2(Class<? extends Member2> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.team2 = inits.isInitialized("team2") ? new QTeam2(forProperty("team2")) : null;
    }

}

