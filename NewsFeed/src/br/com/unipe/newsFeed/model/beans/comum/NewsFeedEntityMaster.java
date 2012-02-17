package br.com.unipe.newsFeed.model.beans.comum;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * @author moacir
 *
 */

@MappedSuperclass
public class NewsFeedEntityMaster implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Version
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastUpdate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
