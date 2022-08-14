package rs.miromaric.plutus.wallet.model;

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
import rs.miromaric.plutus.common.model.HasUuid;

/**
 * Immutable implementation of {@link Wallet}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutableWallet.builder()}.
 */
@Generated(from = "Wallet", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
public final class ImmutableWallet implements Wallet, Serializable {
  private final String uuid;
  private final String userUuid;
  private final String label;
  private final String currency;
  private final BigDecimal amount;
  private final Boolean isActive;

  private ImmutableWallet(ImmutableWallet.Builder builder) {
    this.userUuid = builder.userUuid;
    this.label = builder.label;
    this.currency = builder.currency;
    if (builder.uuid != null) {
      initShim.uuid(builder.uuid);
    }
    if (builder.amount != null) {
      initShim.amount(builder.amount);
    }
    if (builder.isActive != null) {
      initShim.isActive(builder.isActive);
    }
    this.uuid = initShim.getUuid();
    this.amount = initShim.getAmount();
    this.isActive = initShim.isActive();
    this.initShim = null;
  }

  private ImmutableWallet(
      String uuid,
      String userUuid,
      String label,
      String currency,
      BigDecimal amount,
      Boolean isActive) {
    this.uuid = uuid;
    this.userUuid = userUuid;
    this.label = label;
    this.currency = currency;
    this.amount = amount;
    this.isActive = isActive;
    this.initShim = null;
  }

  private static final byte STAGE_INITIALIZING = -1;
  private static final byte STAGE_UNINITIALIZED = 0;
  private static final byte STAGE_INITIALIZED = 1;
  private transient volatile InitShim initShim = new InitShim();

  @Generated(from = "Wallet", generator = "Immutables")
  private final class InitShim {
    private byte uuidBuildStage = STAGE_UNINITIALIZED;
    private String uuid;

    String getUuid() {
      if (uuidBuildStage == STAGE_INITIALIZING) throw new IllegalStateException(formatInitCycleMessage());
      if (uuidBuildStage == STAGE_UNINITIALIZED) {
        uuidBuildStage = STAGE_INITIALIZING;
        this.uuid = Objects.requireNonNull(getUuidInitialize(), "uuid");
        uuidBuildStage = STAGE_INITIALIZED;
      }
      return this.uuid;
    }

    void uuid(String uuid) {
      this.uuid = uuid;
      uuidBuildStage = STAGE_INITIALIZED;
    }

    private byte amountBuildStage = STAGE_UNINITIALIZED;
    private BigDecimal amount;

    BigDecimal getAmount() {
      if (amountBuildStage == STAGE_INITIALIZING) throw new IllegalStateException(formatInitCycleMessage());
      if (amountBuildStage == STAGE_UNINITIALIZED) {
        amountBuildStage = STAGE_INITIALIZING;
        this.amount = Objects.requireNonNull(getAmountInitialize(), "amount");
        amountBuildStage = STAGE_INITIALIZED;
      }
      return this.amount;
    }

    void amount(BigDecimal amount) {
      this.amount = amount;
      amountBuildStage = STAGE_INITIALIZED;
    }

    private byte isActiveBuildStage = STAGE_UNINITIALIZED;
    private Boolean isActive;

    Boolean isActive() {
      if (isActiveBuildStage == STAGE_INITIALIZING) throw new IllegalStateException(formatInitCycleMessage());
      if (isActiveBuildStage == STAGE_UNINITIALIZED) {
        isActiveBuildStage = STAGE_INITIALIZING;
        this.isActive = Objects.requireNonNull(isActiveInitialize(), "isActive");
        isActiveBuildStage = STAGE_INITIALIZED;
      }
      return this.isActive;
    }

    void isActive(Boolean isActive) {
      this.isActive = isActive;
      isActiveBuildStage = STAGE_INITIALIZED;
    }

    private String formatInitCycleMessage() {
      List<String> attributes = new ArrayList<>();
      if (uuidBuildStage == STAGE_INITIALIZING) attributes.add("uuid");
      if (amountBuildStage == STAGE_INITIALIZING) attributes.add("amount");
      if (isActiveBuildStage == STAGE_INITIALIZING) attributes.add("isActive");
      return "Cannot build Wallet, attribute initializers form cycle " + attributes;
    }
  }

  private String getUuidInitialize() {
    return Wallet.super.getUuid();
  }

  private BigDecimal getAmountInitialize() {
    return Wallet.super.getAmount();
  }

  private Boolean isActiveInitialize() {
    return Wallet.super.isActive();
  }

  /**
   * @return The value of the {@code uuid} attribute
   */
  @JsonProperty("uuid")
  @Override
  public String getUuid() {
    InitShim shim = this.initShim;
    return shim != null
        ? shim.getUuid()
        : this.uuid;
  }

  /**
   * @return The value of the {@code userUuid} attribute
   */
  @JsonProperty("userUuid")
  @Override
  public String getUserUuid() {
    return userUuid;
  }

  /**
   * @return The value of the {@code label} attribute
   */
  @JsonProperty("label")
  @Override
  public String getLabel() {
    return label;
  }

  /**
   * @return The value of the {@code currency} attribute
   */
  @JsonProperty("currency")
  @Override
  public String getCurrency() {
    return currency;
  }

  /**
   * @return The value of the {@code amount} attribute
   */
  @JsonProperty("amount")
  @Override
  public BigDecimal getAmount() {
    InitShim shim = this.initShim;
    return shim != null
        ? shim.getAmount()
        : this.amount;
  }

  /**
   * @return The value of the {@code isActive} attribute
   */
  @JsonProperty("isActive")
  @Override
  public Boolean isActive() {
    InitShim shim = this.initShim;
    return shim != null
        ? shim.isActive()
        : this.isActive;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link Wallet#getUuid() uuid} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for uuid
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableWallet withUuid(String value) {
    String newValue = Objects.requireNonNull(value, "uuid");
    if (this.uuid.equals(newValue)) return this;
    return new ImmutableWallet(newValue, this.userUuid, this.label, this.currency, this.amount, this.isActive);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link Wallet#getUserUuid() userUuid} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for userUuid
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableWallet withUserUuid(String value) {
    String newValue = Objects.requireNonNull(value, "userUuid");
    if (this.userUuid.equals(newValue)) return this;
    return new ImmutableWallet(this.uuid, newValue, this.label, this.currency, this.amount, this.isActive);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link Wallet#getLabel() label} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for label
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableWallet withLabel(String value) {
    String newValue = Objects.requireNonNull(value, "label");
    if (this.label.equals(newValue)) return this;
    return new ImmutableWallet(this.uuid, this.userUuid, newValue, this.currency, this.amount, this.isActive);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link Wallet#getCurrency() currency} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for currency
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableWallet withCurrency(String value) {
    String newValue = Objects.requireNonNull(value, "currency");
    if (this.currency.equals(newValue)) return this;
    return new ImmutableWallet(this.uuid, this.userUuid, this.label, newValue, this.amount, this.isActive);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link Wallet#getAmount() amount} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for amount
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableWallet withAmount(BigDecimal value) {
    BigDecimal newValue = Objects.requireNonNull(value, "amount");
    if (this.amount.equals(newValue)) return this;
    return new ImmutableWallet(this.uuid, this.userUuid, this.label, this.currency, newValue, this.isActive);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link Wallet#isActive() isActive} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for isActive
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableWallet withIsActive(Boolean value) {
    Boolean newValue = Objects.requireNonNull(value, "isActive");
    if (this.isActive.equals(newValue)) return this;
    return new ImmutableWallet(this.uuid, this.userUuid, this.label, this.currency, this.amount, newValue);
  }

  /**
   * This instance is equal to all instances of {@code ImmutableWallet} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableWallet
        && equalTo((ImmutableWallet) another);
  }

  private boolean equalTo(ImmutableWallet another) {
    return uuid.equals(another.uuid)
        && userUuid.equals(another.userUuid)
        && label.equals(another.label)
        && currency.equals(another.currency)
        && amount.equals(another.amount)
        && isActive.equals(another.isActive);
  }

  /**
   * Computes a hash code from attributes: {@code uuid}, {@code userUuid}, {@code label}, {@code currency}, {@code amount}, {@code isActive}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + uuid.hashCode();
    h += (h << 5) + userUuid.hashCode();
    h += (h << 5) + label.hashCode();
    h += (h << 5) + currency.hashCode();
    h += (h << 5) + amount.hashCode();
    h += (h << 5) + isActive.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code Wallet} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("Wallet")
        .omitNullValues()
        .add("uuid", uuid)
        .add("userUuid", userUuid)
        .add("label", label)
        .add("currency", currency)
        .add("amount", amount)
        .add("isActive", isActive)
        .toString();
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Generated(from = "Wallet", generator = "Immutables")
  @Deprecated
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json implements Wallet, Serializable {

    private static final long serialVersionUID = 20210707001952693L;
    String uuid;
    String userUuid;
    String label;
    String currency;
    BigDecimal amount;
    Boolean isActive;
    @JsonProperty("uuid")
    public void setUuid(String uuid) {
      this.uuid = uuid;
    }
    @JsonProperty("userUuid")
    public void setUserUuid(String userUuid) {
      this.userUuid = userUuid;
    }
    @JsonProperty("label")
    public void setLabel(String label) {
      this.label = label;
    }
    @JsonProperty("currency")
    public void setCurrency(String currency) {
      this.currency = currency;
    }
    @JsonProperty("amount")
    public void setAmount(BigDecimal amount) {
      this.amount = amount;
    }
    @JsonProperty("isActive")
    public void setIsActive(Boolean isActive) {
      this.isActive = isActive;
    }
    @Override
    public String getUuid() { throw new UnsupportedOperationException(); }
    @Override
    public String getUserUuid() { throw new UnsupportedOperationException(); }
    @Override
    public String getLabel() { throw new UnsupportedOperationException(); }
    @Override
    public String getCurrency() { throw new UnsupportedOperationException(); }
    @Override
    public BigDecimal getAmount() { throw new UnsupportedOperationException(); }
    @Override
    public Boolean isActive() { throw new UnsupportedOperationException(); }
  }

  /**
   * @param json A JSON-bindable data structure
   * @return An immutable value type
   * @deprecated Do not use this method directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  static ImmutableWallet fromJson(Json json) {
    ImmutableWallet.Builder builder = ImmutableWallet.builder();
    if (json.uuid != null) {
      builder.uuid(json.uuid);
    }
    if (json.userUuid != null) {
      builder.userUuid(json.userUuid);
    }
    if (json.label != null) {
      builder.label(json.label);
    }
    if (json.currency != null) {
      builder.currency(json.currency);
    }
    if (json.amount != null) {
      builder.amount(json.amount);
    }
    if (json.isActive != null) {
      builder.isActive(json.isActive);
    }
    return builder.build();
  }

  /**
   * Creates an immutable copy of a {@link Wallet} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable Wallet instance
   */
  public static ImmutableWallet copyOf(Wallet instance) {
    if (instance instanceof ImmutableWallet) {
      return (ImmutableWallet) instance;
    }
    return ImmutableWallet.builder()
        .from(instance)
        .build();
  }

  private static final long serialVersionUID = 20210707001952693L;

  /**
   * Creates a builder for {@link ImmutableWallet ImmutableWallet}.
   * <pre>
   * ImmutableWallet.builder()
   *    .uuid(String) // optional {@link Wallet#getUuid() uuid}
   *    .userUuid(String) // required {@link Wallet#getUserUuid() userUuid}
   *    .label(String) // required {@link Wallet#getLabel() label}
   *    .currency(String) // required {@link Wallet#getCurrency() currency}
   *    .amount(java.math.BigDecimal) // optional {@link Wallet#getAmount() amount}
   *    .isActive(Boolean) // optional {@link Wallet#isActive() isActive}
   *    .build();
   * </pre>
   * @return A new ImmutableWallet builder
   */
  public static ImmutableWallet.Builder builder() {
    return new ImmutableWallet.Builder();
  }

  /**
   * Builds instances of type {@link ImmutableWallet ImmutableWallet}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "Wallet", generator = "Immutables")
  public static final class Builder {
    private static final long INIT_BIT_USER_UUID = 0x1L;
    private static final long INIT_BIT_LABEL = 0x2L;
    private static final long INIT_BIT_CURRENCY = 0x4L;
    private long initBits = 0x7L;

    private String uuid;
    private String userUuid;
    private String label;
    private String currency;
    private BigDecimal amount;
    private Boolean isActive;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code rs.miromaric.plutus.common.model.HasUuid} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(HasUuid instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return this;
    }

    /**
     * Fill a builder with attribute values from the provided {@code rs.miromaric.plutus.wallet.model.Wallet} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(Wallet instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return this;
    }

    private void from(Object object) {
      if (object instanceof HasUuid) {
        HasUuid instance = (HasUuid) object;
        uuid(instance.getUuid());
      }
      if (object instanceof Wallet) {
        Wallet instance = (Wallet) object;
        userUuid(instance.getUserUuid());
        amount(instance.getAmount());
        currency(instance.getCurrency());
        label(instance.getLabel());
        isActive(instance.isActive());
      }
    }

    /**
     * Initializes the value for the {@link Wallet#getUuid() uuid} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Wallet#getUuid() uuid}.</em>
     * @param uuid The value for uuid 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("uuid")
    public final Builder uuid(String uuid) {
      this.uuid = Objects.requireNonNull(uuid, "uuid");
      return this;
    }

    /**
     * Initializes the value for the {@link Wallet#getUserUuid() userUuid} attribute.
     * @param userUuid The value for userUuid 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("userUuid")
    public final Builder userUuid(String userUuid) {
      this.userUuid = Objects.requireNonNull(userUuid, "userUuid");
      initBits &= ~INIT_BIT_USER_UUID;
      return this;
    }

    /**
     * Initializes the value for the {@link Wallet#getLabel() label} attribute.
     * @param label The value for label 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("label")
    public final Builder label(String label) {
      this.label = Objects.requireNonNull(label, "label");
      initBits &= ~INIT_BIT_LABEL;
      return this;
    }

    /**
     * Initializes the value for the {@link Wallet#getCurrency() currency} attribute.
     * @param currency The value for currency 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("currency")
    public final Builder currency(String currency) {
      this.currency = Objects.requireNonNull(currency, "currency");
      initBits &= ~INIT_BIT_CURRENCY;
      return this;
    }

    /**
     * Initializes the value for the {@link Wallet#getAmount() amount} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Wallet#getAmount() amount}.</em>
     * @param amount The value for amount 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("amount")
    public final Builder amount(BigDecimal amount) {
      this.amount = Objects.requireNonNull(amount, "amount");
      return this;
    }

    /**
     * Initializes the value for the {@link Wallet#isActive() isActive} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Wallet#isActive() isActive}.</em>
     * @param isActive The value for isActive 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("isActive")
    public final Builder isActive(Boolean isActive) {
      this.isActive = Objects.requireNonNull(isActive, "isActive");
      return this;
    }

    /**
     * Builds a new {@link ImmutableWallet ImmutableWallet}.
     * @return An immutable instance of Wallet
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutableWallet build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableWallet(this);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_USER_UUID) != 0) attributes.add("userUuid");
      if ((initBits & INIT_BIT_LABEL) != 0) attributes.add("label");
      if ((initBits & INIT_BIT_CURRENCY) != 0) attributes.add("currency");
      return "Cannot build Wallet, some of required attributes are not set " + attributes;
    }
  }
}
