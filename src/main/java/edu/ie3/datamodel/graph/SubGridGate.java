/*
 * © 2021. TU Dortmund University,
 * Institute of Energy Systems, Energy Efficiency and Energy Economics,
 * Research group Distribution grid planning and operation
*/
package edu.ie3.datamodel.graph;

import edu.ie3.datamodel.models.input.NodeInput;
import edu.ie3.datamodel.models.input.connector.ConnectorPort;
import edu.ie3.datamodel.models.input.connector.Transformer2WInput;
import edu.ie3.datamodel.models.input.connector.Transformer3WInput;
import edu.ie3.datamodel.models.input.connector.TransformerInput;
import edu.ie3.datamodel.models.input.container.SubGridContainer;
import java.io.Serializable;
import java.util.Objects;

/**
 * Defines gates between {@link SubGridContainer}s and serves as edge definition for {@link
 * SubGridTopologyGraph}
 */
public class SubGridGate implements Serializable {
  /**
   * Creates a sub grid gate from two winding transformer.
   *
   * @param transformer Two winding transformer to create gate for
   */
  public static SubGridGate fromTransformer2W(Transformer2WInput transformer) {
    return new SubGridGate(transformer, transformer.getNodeA(), transformer.getNodeB());
  }

  /**
   * Creates a sub grid gate from three winding transformer. Define, which of the two superior sub
   * grids should be taken by the inferior port flag.
   *
   * @param transformer   Three winding transformer to create gate for
   * @param inferiorPort  Choose, which 1-to-1-gate should be created
   */
  public static SubGridGate fromTransformer3W(Transformer3WInput transformer, ConnectorPort inferiorPort){
    switch (inferiorPort) {
    case B:
      return new SubGridGate(transformer, transformer.getNodeA(), transformer.getNodeB());
    case C:
      return new SubGridGate(transformer, transformer.getNodeA(), transformer.getNodeC());
    default:
      throw new IllegalArgumentException(
              "Only port "
                      + ConnectorPort.B
                      + " or "
                      + ConnectorPort.C
                      + " can be "
                      + "chosen as inferior port.");
  }
  }

  private final TransformerInput link;
  private final NodeInput superiorNode;
  private final NodeInput inferiorNode;

  /**
   * Create a {@link SubGridGate}
   *
   * @param link          Model, that physically represents the gate
   * @param superiorNode  Upstream node of the gate
   * @param inferiorNode  Downstream node of the gate
   */
  public SubGridGate(TransformerInput link, NodeInput superiorNode, NodeInput inferiorNode) {
    this.link = link;
    this.superiorNode = superiorNode;
    this.inferiorNode = inferiorNode;
  }

  /**
   * Creates a sub grid gate from two winding transformer.
   *
   * @param transformer2w Two winding transformer to create gate for
   * @deprecated Use {@link SubGridGate#fromTransformer2W(Transformer2WInput)} instead
   */
  @Deprecated
  public SubGridGate(Transformer2WInput transformer2w) {
    this.link = transformer2w;
    this.superiorNode = transformer2w.getNodeA();
    this.inferiorNode = transformer2w.getNodeB();
  }

  /**
   * Creates a sub grid gate from three winding transformer. Define, which of the two superior sub
   * grids should be taken by the inferior port flag.
   *
   * @param transformer3W Three winding transformer to create gate for
   * @param inferiorPort Choose, which 1-to-1-gate should be created
   * @deprecated Use {@link SubGridGate#fromTransformer3W(Transformer3WInput, ConnectorPort)} instead
   */
  @Deprecated
  public SubGridGate(Transformer3WInput transformer3W, ConnectorPort inferiorPort) {
    this.link = transformer3W;
    this.superiorNode = transformer3W.getNodeA();
    switch (inferiorPort) {
      case B:
        this.inferiorNode = transformer3W.getNodeB();
        break;
      case C:
        this.inferiorNode = transformer3W.getNodeC();
        break;
      default:
        throw new IllegalArgumentException(
            "Only port "
                + ConnectorPort.B
                + " or "
                + ConnectorPort.C
                + " can be "
                + "chosen as inferior port.");
    }
  }

  public TransformerInput getLink() {
    return link;
  }

  public NodeInput getSuperiorNode() {
    return superiorNode;
  }

  public NodeInput getInferiorNode() {
    return inferiorNode;
  }

  public int getSuperiorSubGrid() {
    return superiorNode.getSubnet();
  }

  public int getInferiorSubGrid() {
    return inferiorNode.getSubnet();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SubGridGate that = (SubGridGate) o;
    return link.equals(that.link)
        && superiorNode.equals(that.superiorNode)
        && inferiorNode.equals(that.inferiorNode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(link, superiorNode, inferiorNode);
  }

  @Override
  public String toString() {
    return "SubGridTopolgyEdge{"
        + "link="
        + link.getClass().getSimpleName()
        + "("
        + link.getUuid()
        + ")"
        + ", superiorNode="
        + superiorNode.getUuid()
        + " subgrid "
        + superiorNode.getSubnet()
        + ", inferiorNode="
        + inferiorNode.getUuid()
        + " subgrid "
        + inferiorNode.getSubnet()
        + '}';
  }
}
