package com.example.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTest is a Querydsl query type for Test
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTest extends EntityPathBase<Test> {

    private static final long serialVersionUID = -1762444199L;

    public static final QTest test = new QTest("test");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> id2 = createNumber("id2", Long.class);

    public final NumberPath<Integer> id3 = createNumber("id3", Integer.class);

    public final NumberPath<Integer> id4 = createNumber("id4", Integer.class);

    public QTest(String variable) {
        super(Test.class, forVariable(variable));
    }

    public QTest(Path<? extends Test> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTest(PathMetadata metadata) {
        super(Test.class, metadata);
    }

}

