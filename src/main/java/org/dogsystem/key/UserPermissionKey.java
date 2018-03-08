package org.dogsystem.key;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.dogsystem.utils.BaseKey;

@Embeddable
public class UserPermissionKey  extends BaseKey{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "cod_per", length = 11, nullable = false)
	private Long codPer;
	
	@Column(name = "cod_user", length = 11, nullable = false)
	private Long codUser;
	
	public UserPermissionKey() {
	}
	
	public UserPermissionKey(Long codPer, Long codUser) {
		super();
		this.codPer = codPer;
		this.codUser = codUser;
	}

	public Long getcodPer() {
		return this.codPer;
	}

	public void setcodPer(Long codPer) {
		this.codPer = codPer;
	}

	public Long getcodUser() {
		return this.codUser;
	}

	public void setcodUser(Long codUser) {
		this.codUser = codUser;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((this.codPer == null) ? 0 : this.codPer.hashCode());
		result = prime * result + ((this.codUser == null) ? 0 : this.codUser.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!super.equals(obj)) {
			return false;
		}

		if (this.getClass() != obj.getClass()) {
			return false;
		}

		UserPermissionKey other = (UserPermissionKey) obj;

		if (this.codPer == null) {
			if (other.codPer != null) {
				return false;
			}
		} else if (!this.codPer.equals(other.codPer)) {
			return false;
		}

		if (this.codUser == null) {
			if (other.codUser != null) {
				return false;
			}
		} else if (!this.codUser.equals(other.codUser)) {
			return false;
		}

		return true;
	}
	
}
