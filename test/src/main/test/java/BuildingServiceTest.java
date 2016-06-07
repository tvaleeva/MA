import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ru.amfitel.task.client.dictionary.Material;
import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.entity.Build;
import ru.amfitel.task.repository.BuildRepository;
import ru.amfitel.task.server.BuildingService;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Bublik on 07.06.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class BuildingServiceTest {
    @Mock
    BuildRepository buildRepository;

    @Test
    public void loadBuildingsTest() {

        BuildingService buildingService = new BuildingService();
        Date date = new Date();
        Build build = new Build();
        build.setName("name");
        build.setAddress("addr");
        build.setDate(date);
        build.setCountFloor(20);
        build.setMaterial(Material.BRICK);
        build.setFloors(Collections.emptyList());

        Build build1 = new Build();
        build1.setName("name");
        build1.setAddress("addr");
        build1.setDate(date);
        build1.setCountFloor(20);
        build1.setMaterial(Material.BRICK);
        build1.setFloors(Collections.emptyList());
        List<Build> iterable = Arrays.asList(build, build1);

        Mockito.when(buildRepository.findAll()).thenReturn(iterable);
        buildingService.buildRepository = buildRepository;
        List<BuildDTO> building = buildingService.loadBuildings();
        Assert.assertEquals(building.size(), 2);

    }
}
