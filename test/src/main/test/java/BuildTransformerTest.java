import org.junit.Assert;
import org.junit.Test;
import ru.amfitel.task.client.dictionary.Material;
import ru.amfitel.task.client.dictionary.ObjectType;
import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.entity.Build;
import ru.amfitel.task.transformer.BuildTransformer;

import java.util.Collections;
import java.util.Date;

/**
 * Created by Bublik on 07.06.2016.
 */
public class BuildTransformerTest {
    @Test
    public void testTransform() {
        Date date = new Date();
        Build build = new Build();
        build.setName("name");
        build.setAddress("addr");
        build.setDate(date);
        build.setCountFloor(20);
        build.setMaterial(Material.BRICK);
        build.setFloors(Collections.emptyList());
        BuildDTO dto = new BuildTransformer().transform(build);
        Assert.assertEquals(dto.getObjectType(), ObjectType.BUILDING);
        Assert.assertEquals(dto.getName(), "name");
        Assert.assertEquals(dto.getAddress(), "addr");
        Assert.assertEquals(dto.getCountFloor(), new Integer(20));
        Assert.assertEquals(dto.getDate(), date);
        Assert.assertEquals(dto.getMaterial(), Material.BRICK);
        Assert.assertTrue(dto.getFloors().isEmpty());
    }

    @Test
    public void testUpdateEntity(){
        Date date = new Date();
        Build build = new Build();
        build.setName("name");
        BuildDTO buildDTO = new BuildDTO();
        buildDTO.setName("nameDto");
        buildDTO.setAddress("addrDto");
        buildDTO.setDate(date);
        buildDTO.setCountFloor(20);
        buildDTO.setMaterial(Material.BRICK);
        buildDTO.setFloors(Collections.emptyList());
        new BuildTransformer().updateEntity(buildDTO,build);
        Assert.assertEquals(build.getName(), "nameDto");
        Assert.assertEquals(build.getAddress(),"addrDto");
        Assert.assertTrue(build.getFloors().isEmpty());
        Assert.assertEquals(build.getDate(),date);
        Assert.assertEquals(build.getMaterial(), Material.BRICK);
        Assert.assertEquals(build.getCountFloor(), new Integer(20));
    }

}
