package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import model.BggGameResponseModel;
import model.BggGamesResponseModel;
import model.BggThingResponseModel;
import model.BggThingsResponseModel;
import model.BggGame;

@Component
public class BoardGameGeekRepository implements IBoardGameGeekRepository{
	@Autowired
	@Qualifier("resttemplate1")
	public RestTemplate collectionTemplate;
	
	@Autowired
	@Qualifier("resttemplate2")
	public RestTemplate thingsTemplate;
	
	@Autowired
	DozerBeanMapper mapper;
	
	private static final String baseUrl = "https://www.boardgamegeek.com/xmlapi2";
	
	@Override
	public List<BggGame> getCollectionByUsername(String username) {
		try {
			ResponseEntity<BggGamesResponseModel> response = collectionTemplate.getForEntity(baseUrl + "/collection?username=" + username, BggGamesResponseModel.class);
			
			while(response.getStatusCode() == HttpStatus.ACCEPTED){
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				response = collectionTemplate.getForEntity(baseUrl + "/collection/" + username, BggGamesResponseModel.class);
			}
			
			List<BggGameResponseModel> games = response.getBody().getItems();
			
			List<BggGame> mappedGames = new ArrayList<BggGame>();
			
			for(BggGameResponseModel game : games) {
				BggGame mappedGame = mapper.map(game, BggGame.class);
				mappedGames.add(mappedGame);
			}
			
			return mappedGames;
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}
	
	@Override
	public List<BggGame> getGames(List<String> bggGameIds) {
		try {
			String commaDelimitedIds = bggGameIds.stream()
					.collect(Collectors.joining(","));
			
			ResponseEntity<BggThingsResponseModel> response = thingsTemplate.getForEntity(baseUrl + "/thing?id=" + commaDelimitedIds, BggThingsResponseModel.class);
			
			List<BggThingResponseModel> games = response.getBody().getThings();
	
	
			return games.stream()
					.map(game -> mapper.map(game, BggGame.class))
					.collect(Collectors.toList());
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}
	
}
