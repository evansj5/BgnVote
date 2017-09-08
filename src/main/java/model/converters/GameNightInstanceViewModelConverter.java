package model.converters;

import org.dozer.DozerConverter;

import model.GameNight;
import model.GameNightInstance;
import viewmodel.gamenight.GameNightInstanceViewModel;

public class GameNightInstanceViewModelConverter extends DozerConverter<GameNightInstance, GameNightInstanceViewModel> {	
	public GameNightInstanceViewModelConverter() {
		super(GameNightInstance.class, GameNightInstanceViewModel.class);
	}

	@Override
	public GameNightInstanceViewModel convertTo(GameNightInstance source, GameNightInstanceViewModel destination) {
		if(destination == null) {
			destination = new GameNightInstanceViewModel();
		}
		
		destination.setId(source.getId());
		destination.setGameNightId(source.getGameNight().getId());
		destination.setDate(source.getDate());
		destination.setState(source.getState());
		return destination;
	}

	@Override
	public GameNightInstance convertFrom(GameNightInstanceViewModel source, GameNightInstance destination) {
		if(destination == null) {
			destination = new GameNightInstance();
		}
		
		GameNight gameNight = new GameNight();
		gameNight.setId(source.getGameNightId());
		destination.setId(source.getId());
		destination.setGameNight(gameNight);
		destination.setDate(source.getDate());
		destination.setState(source.getState());
		return destination;
	}

}
