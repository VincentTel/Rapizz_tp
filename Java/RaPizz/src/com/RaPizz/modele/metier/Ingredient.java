package com.RaPizz.modele.metier;

public class Ingredient {
		private long ID_Ingredient;
		private String designation;
		
		public Ingredient(long ID_Ingredient,String designation)
		{
			this.ID_Ingredient = ID_Ingredient;
			this.designation = designation;
		}

		public Ingredient(String designation)
		{
			this.designation = designation;
		}


		public long getID_Ingredient() {
			return ID_Ingredient;
		}

		public void setID_Ingredient(long iD_Ingredient) {
			ID_Ingredient = iD_Ingredient;
		}

		public String getDesignation() {
			return designation;
		}

		public void setDesignation(String designation) {
			this.designation = designation;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (ID_Ingredient ^ (ID_Ingredient >>> 32));
			result = prime * result + ((designation == null) ? 0 : designation.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Ingredient other = (Ingredient) obj;
			if (ID_Ingredient != other.ID_Ingredient)
				return false;
			if (designation == null) {
				if (other.designation != null)
					return false;
			} else if (!designation.equals(other.designation))
				return false;
			return true;
		}
		
}
