/*
 * © 2020. TU Dortmund University,
 * Institute of Energy Systems, Energy Efficiency and Energy Economics,
 * Research group Distribution grid planning and operation
*/
package edu.ie3.io.factory;

import edu.ie3.exceptions.FactoryException;
import edu.ie3.models.UniqueEntity;
import edu.ie3.util.TimeTools;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @version 0.1
 * @since 28.01.20
 */
abstract class EntityFactory<T extends UniqueEntity, D extends EntityData> {
  public final Logger log = LogManager.getLogger(this.getClass());

  protected final List<Class<? extends T>> classes;

  public EntityFactory(Class<? extends T>... allowedClasses) {
    this.classes = Arrays.asList(allowedClasses);
    TimeTools.initialize(ZoneId.of("UTC"), Locale.GERMANY, "yyyy-MM-dd HH:mm:ss");
  }

  public abstract Optional<T> getEntity(D entityData);

  protected abstract List<Set<String>> getFields(D entityData);

  protected abstract T buildModel(D simpleEntityData);

  public List<Class<? extends T>> classes() {
    return classes;
  }

  protected TreeSet<String> newSet(String... items) {
    TreeSet<String> set = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
    set.addAll(Arrays.asList(items));
    return set;
  }

  protected TreeSet<String> expandSet(Set<String> set, String... more) {
    TreeSet<String> newSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
    newSet.addAll(set);
    newSet.addAll(Arrays.asList(more));
    return newSet;
  }

  /**
   * Validates the factory specific constructor parameters in two ways. 1) the biggest set of the
   * provided field sets is compared against fields the class implements. If this test passes then
   * we know for sure that the field names at least in the biggest constructor are equal to the
   * provided factory strings 2) if 1) passes, the provided entity data (which is equal to the data
   * e.g. read from the outside) is compared to all available constructor parameters provided by the
   * fieldSets Array. If we find exactly one constructor, that matches the field names we can
   * proceed. Otherwise a detailed exception message is thrown.
   *
   * @param entityData the entity containing at least the entity class as well a mapping of the
   *     provided field name strings to its value (e.g. a headline of a csv -> column values)
   * @param fieldSets a set containing all available constructor combinations as field names
   * @return the number of the set in the fieldSets array that fits the provided entityData
   */
  protected int validateParameters(EntityData entityData, Set<String>... fieldSets) {

    Map<String, String> fieldsToValues = entityData.getFieldsToValues();

    // get all sets that match the fields to attributes
    List<Set<String>> validFieldSets =
        Arrays.stream(fieldSets)
            .filter(x -> x.equals(fieldsToValues.keySet()))
            .collect(Collectors.toList());

    if (validFieldSets.size() == 1) {
      // if we can identify a unique parameter set for a constructor, we take it and return the
      // index
      Set<String> validFieldSet = validFieldSets.get(0);
      return Arrays.asList(fieldSets).indexOf(validFieldSet);
    } else {
      // build the exception string with extensive debug information
      String providedFieldMapString =
          fieldsToValues.keySet().stream()
              .map(key -> key + " -> " + fieldsToValues.get(key))
              .collect(Collectors.joining(","));

      String providedKeysString = "[" + String.join(", ", fieldsToValues.keySet()) + "]";

      String possibleOptions = getFieldsString(fieldSets).toString();

      throw new FactoryException(
          "The provided fields "
              + providedKeysString
              + " with data {"
              + providedFieldMapString
              + "}"
              + " are invalid for instance of "
              + entityData.getEntityClass().getSimpleName()
              + ". \nThe following fields to be passed to a constructor of "
              + entityData.getEntityClass().getSimpleName()
              + " are possible:\n"
              + possibleOptions);
    }
  }

  private StringBuilder getFieldsString(Set<String>... fieldSets) {
    StringBuilder possibleOptions = new StringBuilder();
    for (int i = 0; i < fieldSets.length; i++) {
      Set<String> fieldSet = fieldSets[i];
      String option = i + ": [" + String.join(", ", fieldSet) + "]\n";
      possibleOptions.append(option);
    }
    return possibleOptions;
  }
}