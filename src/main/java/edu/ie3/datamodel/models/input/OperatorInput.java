/*
 * © 2020. TU Dortmund University,
 * Institute of Energy Systems, Energy Efficiency and Energy Economics,
 * Research group Distribution grid planning and operation
*/
package edu.ie3.datamodel.models.input;

import java.util.Objects;
import java.util.UUID;

/** Describes an operator, that operates assets */
public class OperatorInput extends InputEntity {

  public static final OperatorInput NO_OPERATOR_ASSIGNED =
      new OperatorInput(UUID.randomUUID(), "NO_OPERATOR_ASSIGNED");

  /** The name of this operator */
  private final String name;

  /** @param name of this operator */
  public OperatorInput(UUID uuid, String name) {
    super(uuid);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    OperatorInput that = (OperatorInput) o;
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name);
  }

  @Override
  public String toString() {
    return "OperatorInput{" +
            "name='" + name + '\'' +
            '}';
  }
}
