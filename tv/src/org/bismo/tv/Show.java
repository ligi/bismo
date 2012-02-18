package org.bismo.tv;
public class Show {
		private String name;
		private String intent_action;
		private String param;
		private int totalVotes;
		
		public Show(String name,String intent_action,String param) {
			this.name=name;
			this.intent_action=intent_action;
			this.param=param;
		}
				
		public String getName(){
			return name;
		}
		
		public String getIntentAction() {
			return intent_action;
		}

		public String getParam() {
			return param;
		}

		public int getTotalVotes() {
			return totalVotes;
		}

		public void setTotalVotes(int totalVotes) {
			this.totalVotes = totalVotes;
		}
	}