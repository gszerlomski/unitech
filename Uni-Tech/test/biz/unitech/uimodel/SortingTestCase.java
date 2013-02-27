package biz.unitech.uimodel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import biz.unitech.datamodel.Grip;
import biz.unitech.uimodel.FittingDescUIModel;



public class SortingTestCase {

@Test
public void test() {
  List<Grip> list = new ArrayList<Grip>();
  
  list.add(new Grip(12, "12"));
  list.add(new Grip(25, "25"));
  list.add(new Grip(3, "3"));
  list.add(new Grip(22, "25"));
  
  FittingDescUIModel model = new FittingDescUIModel(null,  null, null, null, list, null);
  
  assertTrue("Number of element is incorrect", model.grips.size() == 3);
  
  Collections.sort(model.grips);
  assertEquals(22, model.grips.get(2).getGripOrderCode());
  
}

}