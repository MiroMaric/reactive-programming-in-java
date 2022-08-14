package rs.miromaric.plutus.wallet.model.payout;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.immutables.value.Generated;
import rs.miromaric.plutus.wallet.model.Wallet;

/**
 * Immutable implementation of {@link WPayOutResponse}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutableWPayOutResponse.builder()}.
 */
@Generated(from = "WPayOutResponse", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
public final class ImmutableWPayOutResponse
    implements WPayOutResponse, Serializable {
  private final Wallet wallet;
  private final WPayOutStatus status;

  private ImmutableWPayOutResponse(
      Wallet wallet,
      WPayOutStatus status) {
    this.wallet = wallet;
    this.status = status;
  }

  /**
   * @return The value of the {@code wallet} attribute
   */
  @JsonProperty("wallet")
  @Override
  public Wallet getWallet() {
    return wallet;
  }

  /**
   * @return The value of the {@code status} attribute
   */
  @JsonProperty("status")
  @Override
  public WPayOutStatus getStatus() {
    return status;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link WPayOutResponse#getWallet() wallet} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for wallet
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableWPayOutResponse withWallet(Wallet value) {
    if (this.wallet == value) return this;
    Wallet newValue = Objects.requireNonNull(value, "wallet");
    return new ImmutableWPayOutResponse(newValue, this.status);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link WPayOutResponse#getStatus() status} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for status
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableWPayOutResponse withStatus(WPayOutStatus value) {
    if (this.status == value) return this;
    WPayOutStatus newValue = Objects.requireNonNull(value, "status");
    if (this.status.equals(newValue)) return this;
    return new ImmutableWPayOutResponse(this.wallet, newValue);
  }

  /**
   * This instance is equal to all instances of {@code ImmutableWPayOutResponse} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableWPayOutResponse
        && equalTo((ImmutableWPayOutResponse) another);
  }

  private boolean equalTo(ImmutableWPayOutResponse another) {
    return wallet.equals(another.wallet)
        && status.equals(another.status);
  }

  /**
   * Computes a hash code from attributes: {@code wallet}, {@code status}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + wallet.hashCode();
    h += (h << 5) + status.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code WPayOutResponse} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("WPayOutResponse")
        .omitNullValues()
        .add("wallet", wallet)
        .add("status", status)
        .toString();
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Generated(from = "WPayOutResponse", generator = "Immutables")
  @Deprecated
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json
      implements WPayOutResponse, Serializable {

    private static final long serialVersionUID = 202107072355659593L;
    Wallet wallet;
    WPayOutStatus status;
    @JsonProperty("wallet")
    public void setWallet(Wallet wallet) {
      this.wallet = wallet;
    }
    @JsonProperty("status")
    public void setStatus(WPayOutStatus status) {
      this.status = status;
    }
    @Override
    public Wallet getWallet() { throw new UnsupportedOperationException(); }
    @Override
    public WPayOutStatus getStatus() { throw new UnsupportedOperationException(); }
  }

  /**
   * @param json A JSON-bindable data structure
   * @return An immutable value type
   * @deprecated Do not use this method directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  static ImmutableWPayOutResponse fromJson(Json json) {
    ImmutableWPayOutResponse.Builder builder = ImmutableWPayOutResponse.builder();
    if (json.wallet != null) {
      builder.wallet(json.wallet);
    }
    if (json.status != null) {
      builder.status(json.status);
    }
    return builder.build();
  }

  /**
   * Creates an immutable copy of a {@link WPayOutResponse} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable WPayOutResponse instance
   */
  public static ImmutableWPayOutResponse copyOf(WPayOutResponse instance) {
    if (instance instanceof ImmutableWPayOutResponse) {
      return (ImmutableWPayOutResponse) instance;
    }
    return ImmutableWPayOutResponse.builder()
        .from(instance)
        .build();
  }

  private static final long serialVersionUID = 202107072355659593L;

  /**
   * Creates a builder for {@link ImmutableWPayOutResponse ImmutableWPayOutResponse}.
   * <pre>
   * ImmutableWPayOutResponse.builder()
   *    .wallet(rs.miromaric.plutus.wallet.model.Wallet) // required {@link WPayOutResponse#getWallet() wallet}
   *    .status(rs.miromaric.plutus.wallet.model.payout.WPayOutStatus) // required {@link WPayOutResponse#getStatus() status}
   *    .build();
   * </pre>
   * @return A new ImmutableWPayOutResponse builder
   */
  public static ImmutableWPayOutResponse.Builder builder() {
    return new ImmutableWPayOutResponse.Builder();
  }

  /**
   * Builds instances of type {@link ImmutableWPayOutResponse ImmutableWPayOutResponse}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "WPayOutResponse", generator = "Immutables")
  public static final class Builder {
    private static final long INIT_BIT_WALLET = 0x1L;
    private static final long INIT_BIT_STATUS = 0x2L;
    private long initBits = 0x3L;

    private Wallet wallet;
    private WPayOutStatus status;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code WPayOutResponse} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(WPayOutResponse instance) {
      Objects.requireNonNull(instance, "instance");
      wallet(instance.getWallet());
      status(instance.getStatus());
      return this;
    }

    /**
     * Initializes the value for the {@link WPayOutResponse#getWallet() wallet} attribute.
     * @param wallet The value for wallet 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("wallet")
    public final Builder wallet(Wallet wallet) {
      this.wallet = Objects.requireNonNull(wallet, "wallet");
      initBits &= ~INIT_BIT_WALLET;
      return this;
    }

    /**
     * Initializes the value for the {@link WPayOutResponse#getStatus() status} attribute.
     * @param status The value for status 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("status")
    public final Builder status(WPayOutStatus status) {
      this.status = Objects.requireNonNull(status, "status");
      initBits &= ~INIT_BIT_STATUS;
      return this;
    }

    /**
     * Builds a new {@link ImmutableWPayOutResponse ImmutableWPayOutResponse}.
     * @return An immutable instance of WPayOutResponse
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutableWPayOutResponse build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableWPayOutResponse(wallet, status);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_WALLET) != 0) attributes.add("wallet");
      if ((initBits & INIT_BIT_STATUS) != 0) attributes.add("status");
      return "Cannot build WPayOutResponse, some of required attributes are not set " + attributes;
    }
  }
}
