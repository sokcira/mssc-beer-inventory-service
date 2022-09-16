package guru.sfg.beer.inventory.service.bootstrap;

import guru.sfg.beer.inventory.service.domain.BeerInventory;
import guru.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by jt on 2019-06-07.
 */
@Slf4j
@Component
public class BeerInventoryBootstrap implements CommandLineRunner {
    public static final UUID BEER_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
    public static final String BEER_1_UPC = "0631234200036";
    public static final UUID BEER_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
    public static final String BEER_2_UPC = "0083783375213";

    private final BeerInventoryRepository beerInventoryRepository;

    public BeerInventoryBootstrap(BeerInventoryRepository beerInventoryRepository) {
        this.beerInventoryRepository = beerInventoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(beerInventoryRepository.count() == 0){
            System.out.println("is beeer inventory repo 0 " + beerInventoryRepository.count());
            loadInitialInv();
        }
    }

    private void loadInitialInv() {
        BeerInventory bI1 = BeerInventory
                .builder()
                .beerId(BEER_1_UUID)
                .upc(BEER_1_UPC)
                .quantityOnHand(22)
                .build();

        BeerInventory bI2 = BeerInventory
                .builder()
                .beerId(BEER_2_UUID)
                .upc(BEER_2_UPC)
                .quantityOnHand(39)
                .build();

        beerInventoryRepository.save(bI1);
        beerInventoryRepository.save(bI2);
    }
}
