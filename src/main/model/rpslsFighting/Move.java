package model.rpslsFighting;

public enum Move {

	ROCK {
		@Override
		public boolean beats(Move move) {
			return (move == SCISSOR || move == LIZARD);
		}

		@Override
		public String getName() {
			return "Rock";
		}
	},
	PAPER {
		@Override
		public boolean beats(Move move) {
			return (move == ROCK || move == SPOCK);
		}

		@Override
		public String getName() {
			return "Paper";
		}
	},
	SCISSOR {
		@Override
		public boolean beats(Move move) {
			return (move == PAPER || move == LIZARD);
		}

		@Override
		public String getName() {
			return "Scissor";
		}
	},
	SPOCK {
		@Override
		public boolean beats(Move move) {
			return (move == SCISSOR || move == ROCK);
		}

		@Override
		public String getName() {
			return "Spock";
		}
	},
	LIZARD {
		@Override
		public boolean beats(Move move) {
			return (move == SPOCK || move == PAPER);
		}

		@Override
		public String getName() {
			return "Lizard";
		}
	};

	public abstract boolean beats(Move move);

	public abstract String getName();

}
