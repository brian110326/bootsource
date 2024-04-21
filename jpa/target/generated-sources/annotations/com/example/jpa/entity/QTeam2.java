package com.example.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTeam2 is a Querydsl query type for Team2
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeam2 extends EntityPathBase<Team2> {

    private static final long serialVersionUID = 1198787214L;

    public static final QTeam2 team2 = new QTeam2("team2");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QTeam2(String variable) {
        super(Team2.class, forVariable(variable));
    }

    public QTeam2(Path<? extends Team2> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeam2(PathMetadata metadata) {
        super(Team2.class, metadata);
    }

}

