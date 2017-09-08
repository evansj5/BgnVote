package model.converters;

import org.dozer.DozerConverter;

import model.BggGame;
import model.BggThingResponseModel;

public class BggGameConverter extends DozerConverter<BggThingResponseModel, BggGame>{

	
	
	public BggGameConverter() {
		super(BggThingResponseModel.class, BggGame.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BggGame convertTo(BggThingResponseModel source, BggGame destination) {
		if(destination == null) {
			destination = new BggGame();
		}
		
		destination.setId(source.getId());
		destination.setImage(source.getImage());
		destination.setThumbnail(source.getThumbnail());
		destination.setName(source.getNames()
			.stream()
			.filter(n -> n.getType().equalsIgnoreCase("primary"))
			.findFirst().orElse(null).getValue());
		
		return destination;
	}

	@Override
	public BggThingResponseModel convertFrom(BggGame source, BggThingResponseModel destination) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
