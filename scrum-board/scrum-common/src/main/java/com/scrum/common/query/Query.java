package com.scrum.common.query;

public interface Query<U, T> {
	
	public abstract T executeQuery(U i);

}
