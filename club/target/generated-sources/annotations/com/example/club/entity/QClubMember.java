package com.example.club.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClubMember is a Querydsl query type for ClubMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClubMember extends EntityPathBase<ClubMember> {

    private static final long serialVersionUID = 569238996L;

    public static final QClubMember clubMember = new QClubMember("clubMember");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    public final BooleanPath fromSocial = createBoolean("fromSocial");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final SetPath<com.example.club.constant.ClubMemberRole, EnumPath<com.example.club.constant.ClubMemberRole>> roleSet = this.<com.example.club.constant.ClubMemberRole, EnumPath<com.example.club.constant.ClubMemberRole>>createSet("roleSet", com.example.club.constant.ClubMemberRole.class, EnumPath.class, PathInits.DIRECT2);

    public QClubMember(String variable) {
        super(ClubMember.class, forVariable(variable));
    }

    public QClubMember(Path<? extends ClubMember> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClubMember(PathMetadata metadata) {
        super(ClubMember.class, metadata);
    }

}

