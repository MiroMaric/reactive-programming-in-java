package rs.miromaric.plutus.wallet.model.payout;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.immutables.value.Generated;

/**
 * Immutable implementation of {@link WPayOutRequest}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutableWPayOutRequest.builder()}.
 */
@Generated(from = "WPayOutRequest", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
public final class ImmutableWPayOutRequest
    implements WPayOutRequest, Serializable {
  private final String walletUuid;
  private final BigDecimal amount;

  private ImmutableWPayOutRequest(String walletUuid, BigDecimal amount) {
    this.walletUuid = walletUuid;
    this.amount = amount;
  }

  /**
   * @return The value of the {@code walletUuid} attribute
   */
  @JsonProperty("walletUuid")
  @Override
  public String getWalletUuid() {
    return walletUuid;
  }

  /**
   * @return The value of the {@code amount} attribute
   */
  @JsonProperty("amount")
  @Override
  public BigDecimal getAmount() {
    return amount;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link WPayOutRequest#getWalletUuid() walletUuid} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for walletUuid
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableWPayOutRequest withWalletUuid(String value) {
    String newValue = Objects.requireNonNull(value, "walletUuid");
    if (this.walletUuid.equals(newValue)) return this;
    return new ImmutableWPayOutRequest(newValue, this.amount);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link WPayOutRequest#getAmount() amount} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for amount
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableWPayOutRequest withAmount(BigDecimal value) {
    BigDecimal newValue = Objects.requireNonNull(value, "amount");
    if (this.amount.equals(newValue)) return this;
    return new ImmutableWPayOutRequest(this.walletUuid, newValue);
  }

  /**
   * This instance is equal to all instances of {@code ImmutableWPayOutRequest} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableWPayOutRequest
        && equalTo((ImmutableWPayOutRequest) another);
  }

  private boolean equalTo(ImmutableWPayOutRequest another) {
    return walletUuid.equals(another.walletUuid)
        && amount.equals(another.amount);
  }

  /**
   * Computes a hash code from attributes: {@code walletUuid}, {@code amount}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + walletUuid.hashCode();
    h += (h << 5) + amount.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code WPayOutRequest} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("WPayOutRequest")
        .omitNullValues()
        .add("walletUuid", walletUuid)
        .add("amount", amount)
        .toString();
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Generated(from = "WPayOutRequest", generator = "Immutables")
  @Deprecated
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json
      implements WPayOutRequest, Serializable {

    private static final long serialVersionUID = 202107072355659022L;
    String walletUuid;
    BigDecimal amount;
    @JsonProperty("walletUuid")
    public void setWalletUuid(String walletUuid) {
      this.walletUuid = walletUuid;
    }
    @JsonProperty("amount")
    public void setAmount(BigDecimal amount) {
      this.amount = amount;
    }
    @Override
    public String getWalletUuid() { throw new UnsupportedOperationException(); }
    @Override
    public BigDecimal getAmount() { throw new UnsupportedOperationException(); }
  }

  /**
   * @param json A JSON-bindable data structure
   * @return An immutable value type
   * @deprecated Do not use this method directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  static ImmutableWPayOutRequest fromJson(Json json) {
    ImmutableWPayOutRequest.Builder builder = ImmutableWPayOutRequest.builder();
    if (json.walletUuid != null) {
      builder.walletUuid(json.walletUuid);
    }
    if (json.amount != null) {
      builder.amount(json.amount);
    }
    return builder.build();
  }

  /**
   * Creates an immutable copy of a {@link WPayOutRequest} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable WPayOutRequest instance
   */
  public static ImmutableWPayOutRequest copyOf(WPayOutRequest instance) {
    if (instance instanceof ImmutableWPayOutRequest) {
      return (ImmutableWPayOutRequest) instance;
    }
    return ImmutableWPayOutRequest.builder()
        .from(instance)
        .build();
  }

  private static final long serialVersionUID = 202107072355659022L;

  /**
   * Creates a builder for {@link ImmutableWPayOutRequest ImmutableWPayOutRequest}.
   * <pre>
   * ImmutableWPayOutRequest.builder()
   *    .walletUuid(String) // required {@link WPayOutRequest#getWalletUuid() walletUuid}
   *    .amount(java.math.BigDecimal) // required {@link WPayOutRequest#getAmount() amount}
   *    .build();
   * </pre>
   * @return A new ImmutableWPayOutRequest builder
   */
  public static ImmutableWPayOutRequest.Builder builder() {
    return new ImmutableWPayOutRequest.Builder();
  }

  /**
   * Builds instances of type {@link ImmutableWPayOutRequest ImmutableWPayOutRequest}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "WPayOutRequest", generator = "Immutables")
  public static final class Builder {
    private static final long INIT_BIT_WALLET_UUID = 0x1L;
    private static final long INIT_BIT_AMOUNT = 0x2L;
    private long initBits = 0x3L;

    private String walletUuid;
    private BigDecimal amount;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code WPayOutRequest} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(WPayOutRequest instance) {
      Objects.requireNonNull(instance, "instance");
      walletUuid(instance.getWalletUuid());
      amount(instance.getAmount());
      return this;
    }

    /**
     * Initializes the value for the {@link WPayOutRequest#getWalletUuid() walletUuid} attribute.
     * @param walletUuid The value for walletUuid 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("walletUuid")
    public final Builder walletUuid(String walletUuid) {
      this.walletUuid = Objects.requireNonNull(walletUuid, "walletUuid");
      initBits &= ~INIT_BIT_WALLET_UUID;
      return this;
    }

    /**
     * Initializes the value for the {@link WPayOutRequest#getAmount() amount} attribute.
     * @param amount The value for amount 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("amount")
    public final Builder amount(BigDecimal amount) {
      this.amount = Objects.requireNonNull(amount, "amount");
      initBits &= ~INIT_BIT_AMOUNT;
      return this;
    }

    /**
     * Builds a new {@link ImmutableWPayOutRequest ImmutableWPayOutRequest}.
     * @return An immutable instance of WPayOutRequest
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutableWPayOutRequest build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableWPayOutRequest(walletUuid, amount);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_WALLET_UUID) != 0) attributes.add("walletUuid");
      if ((initBits & INIT_BIT_AMOUNT) != 0) attributes.add("amount");
      return "Cannot build WPayOutRequest, some of required attributes are not set " + attributes;
    }
  }
}
