package com.techno.studentguide.model;

/**
 * Created by Android on 5/13/2016.
 */
public class Area
	{
		private String area_id;
		private String area_name_en;
		private String area_name_ar;

		public String getArea_name_ar ()
			{
				return area_name_ar;
			}

		public void setArea_name_ar ( String area_name_ar )
			{
				this.area_name_ar = area_name_ar;
			}

		public String getArea_name_en ()
			{
				return area_name_en;
			}

		public void setArea_name_en ( String area_name_en )
			{
				this.area_name_en = area_name_en;
			}

		public String getArea_id ()
			{
				return area_id;
			}

		public void setArea_id ( String area_id )
			{
				this.area_id = area_id;
			}


	}
