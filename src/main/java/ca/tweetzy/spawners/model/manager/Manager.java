package ca.tweetzy.spawners.model.manager;

import lombok.NonNull;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Date Created: May 04 2022
 * Time Created: 11:17 a.m.
 *
 * @author Kiran Hart
 */
public abstract class Manager<K, T> {

	protected final Map<K, T> contents = new ConcurrentHashMap<>();

	public abstract void add(@NonNull final T t);

	public abstract void remove(@NonNull final K key);

	public abstract T find(@NonNull final K key);

	public abstract void load();

	public List<T> getContents() {
		return List.copyOf(this.contents.values());
	}
}
