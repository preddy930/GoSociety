package com.gosociety.server.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gosociety.server.common.dao.RecommendationDao;
import com.gosociety.server.common.model.PlaceDetailRecommendation;
import com.gosociety.server.common.model.RecommendationDetail;
import com.gosociety.server.common.model.Recommender;
import com.gosociety.server.services.json.GoSoPlaceDetailResponse;
import com.gosociety.server.services.model.PlaceDetailsRequestInput;

@Controller
@RequestMapping("/placedetails")
public class GoSoPlaceDetailsController {
	
	private RecommendationDao recommendationdao;

	@RequestMapping(method=RequestMethod.POST )
	@ResponseBody
	public GoSoPlaceDetailResponse placeDetails(@RequestBody PlaceDetailsRequestInput request) {
		
		GoSoPlaceDetailResponse response = new GoSoPlaceDetailResponse();
		
		commonPlaceDetailsLogic(request, response);
		
		return response;
	}
	
	private void commonPlaceDetailsLogic(PlaceDetailsRequestInput request, GoSoPlaceDetailResponse response) {
		
		List<PlaceDetailRecommendation>recommendations = new ArrayList<PlaceDetailRecommendation>();
		
		List<RecommendationDetail>recommendationdetails = recommendationdao.getAllRecommendations(request.getPid());
		List<Recommender>recommenders = recommendationdao.getAllRecommenders();

		for (RecommendationDetail reco:recommendationdetails){
			for(Recommender r:recommenders) {
				if(reco.getRecommender().equals(r.getId())) {
					PlaceDetailRecommendation pdr = new PlaceDetailRecommendation();
					r.setCalloutImageUrl("");
					r.setFilterImageUrl("");
					r.setMapMarkerImageUrl("");
					pdr.setRecommendation(reco);
					pdr.setRecommender(r);
					recommendations.add(pdr);
				}
			}
		}
		
		response.setRecommendations(recommendations);
	}

	public RecommendationDao getRecommendationdao() {
		return recommendationdao;
	}

	public void setRecommendationdao(RecommendationDao recommendationdao) {
		this.recommendationdao = recommendationdao;
	}
}
