package com.shooterj.plugin.cache.support;

import com.shooterj.plugin.cache.enums.CacheOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CacheMessage implements Serializable {

	private Object serverId;

	private String cacheName;

	private CacheOperation operation;

	private Object key;

}
