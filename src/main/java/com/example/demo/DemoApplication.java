package com.example.demo;

import com.example.demo.service.HomeService;
import com.example.demo.service.RandomGeneratorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.Random;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean //jesli trafisz na metode oadnotowana bean to efekt tej metody (to co ona zwraca) dorzuc sobie do kontekstu
	public HomeService homeService() {
		HomeService homeService = new HomeService("Hello world from bean!");
		return homeService;
	}

	//tego beana uzyjemy ponizej jako argument do innego beana
	@Bean
	public Random random() {
		return new Random();
	}

	//dwa beany tej samej klasie. W home controller spring zmatchuje nam zmienna randomGeneratorService z pierwszym
	//beanem, bo jej nazwa zgadza sie z nazwa pierwszego beana
	//jesli nie bedzie matchujacej nazwy beana, to wezmie tego, ktorego typ zgadza sie z typem zmienej randomGeneratorService
	//w klasie home controller - w tym przypadku program sie wysypie, bo sa dwa beany o odpowiadajacej springowi klasie
	//wtedy mozna w klasie home controller, przy zmziennej randomGeneratorService zadac adnotacje @Qualifier("nazwa"),
	//ktora wskazae nam ktorego beana spring na uzyc
	//mozna tez przy beanie uzyc adnotacji @Primary, zeby wskazac ktory bean jest mocniejszy
	//ale i tak wskazanie adnotacja Qualifier bedzie silniejsze
	//podsumowujac, hierarhia wyszukiwania odpowiedniego beana jest nastepujaca (od najsilniejszego)
	//po klasie, po nazwie, po primary; a jesli jest Qualifier to spring szuka po Qualifier i tylko po niej (typ)
	//nadal musi sie zgadzac - jak nie znajdzie to sie wysypie
	@Bean
	@Profile("dev")  //jesli apka zostanie odpalona w profilem dev, to uzyty zostanie ten bean
	public RandomGeneratorService randomGeneratorService(Random random) { //ten argument tez musi byc zarejestrowany w kontekscie
		RandomGeneratorService randomGeneratorService = new RandomGeneratorService(random);
		randomGeneratorService.setBound(100);
		randomGeneratorService.setShift(50);
		return randomGeneratorService;
	}

	@Bean
	@Profile("prod")
	public RandomGeneratorService bigGenerator (Random random) {
		RandomGeneratorService randomGeneratorService = new RandomGeneratorService(random);
		randomGeneratorService.setBound(10000);
		randomGeneratorService.setShift(500);
		return randomGeneratorService;
	}
}
