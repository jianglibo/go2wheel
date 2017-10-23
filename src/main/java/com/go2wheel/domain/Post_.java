package com.go2wheel.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-23T14:35:06.095+0800")
@StaticMetamodel(Post.class)
public class Post_ {
	public static volatile SingularAttribute<Post, Object> creator;
	public static volatile CollectionAttribute<Post, Object> media;
	public static volatile CollectionAttribute<Post, Object> postShares;
}
