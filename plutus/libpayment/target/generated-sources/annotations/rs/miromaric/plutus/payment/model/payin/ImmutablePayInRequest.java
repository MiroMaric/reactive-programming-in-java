package rs.miromaric.plutus.payment.model.payin;

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
 * Immutable implementation of {@link PayInRequest}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutablePayInRequest.builder()}.
 */
@Generated(from = "PayInRequest", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
public final class ImmutablePayInRequest
    implements PayInRequest, Serializable {
  private final String userId;
  private final String walletId;
  private final String currency;
  private final String token;
  private final BigDecimal amount;

  private ImmutablePayInRequest(
      String userId,
      String walletId,
      String currency,
      String token,
      BigDecimal amount) {
    this.userId = userId;
    this.walletId = walletId;
    this.currency = currency;
    this.token = token;
    this.amount = amount;
  }

  /**
   * @return The value of the {@code userId} attribute
   */
  @JsonProperty("userId")
  @Override
  public String getUserId() {
    return userId;
  }

  /**
   * @return The value of the {@code walletId} attribute
   */
  @JsonProperty("walletId")
  @Override
  public String getWalletId() {
    return walletId;
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
   * @return The value of the {@code token} attribute
   */
  @JsonProperty("token")
  @Override
  public String getToken() {
    return token;
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
   * Copy the current immutable object by setting a value for the {@link PayInRequest#getUserId() userId} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for userId
   * @return A modified copy of the {@code this} object
   */
  public final ImmutablePayInRequest withUserId(String value) {
    String newValue = Objects.requireNonNull(value, "userId");
    if (this.userId.equals(newValue)) return this;
    return new ImmutablePayInRequest(newValue, this.walletId, this.currency, this.token, this.amount);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link PayInRequest#getWalletId() walletId} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for walletId
   * @return A modified copy of the {@code this} object
   */
  public final ImmutablePayInRequest withWalletId(String value) {
    String newValue = Objects.requireNonNull(value, "walletId");
    if (this.walletId.equals(newValue)) return this;
    return new ImmutablePayInRequest(this.userId, newValue, this.currency, this.token, this.amount);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link PayInRequest#getCurrency() currency} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for currency
   * @return A modified copy of the {@code this} object
   */
  public final ImmutablePayInRequest withCurrency(String value) {
    String newValue = Objects.requireNonNull(value, "currency");
    if (this.currency.equals(newValue)) return this;
    return new ImmutablePayInRequest(this.userId, this.walletId, newValue, this.token, this.amount);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link PayInRequest#getToken() token} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for token
   * @return A modified copy of the {@code this} object
   */
  public final ImmutablePayInRequest withToken(String value) {
    String newValue = Objects.requireNonNull(value, "token");
    if (this.token.equals(newValue)) return this;
    return new ImmutablePayInRequest(this.userId, this.walletId, this.currency, newValue, this.amount);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link PayInRequest#getAmount() amount} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for amount
   * @return A modified copy of the {@code this} object
   */
  public final ImmutablePayInRequest withAmount(BigDecimal value) {
    BigDecimal newValue = Objects.requireNonNull(value, "amount");
    if (this.amount.equals(newValue)) return this;
    return new ImmutablePayInRequest(this.userId, this.walletId, this.currency, this.token, newValue);
  }

  /**
   * This instance is equal to all instances of {@code ImmutablePayInRequest} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutablePayInRequest
        && equalTo((ImmutablePayInRequest) another);
  }

  private boolean equalTo(ImmutablePayInRequest another) {
    return userId.equals(another.userId)
        && walletId.equals(another.walletId)
        && currency.equals(another.currency)
        && token.equals(another.token)
        && amount.equals(another.amount);
  }

  /**
   * Computes a hash code from attributes: {@code userId}, {@code walletId}, {@code currency}, {@code token}, {@code amount}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + userId.hashCode();
    h += (h << 5) + walletId.hashCode();
    h += (h << 5) + currency.hashCode();
    h += (h << 5) + token.hashCode();
    h += (h << 5) + amount.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code PayInRequest} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("PayInRequest")
        .omitNullValues()
        .add("userId", userId)
        .add("walletId", walletId)
        .add("currency", currency)
        .add("token", token)
        .add("amount", amount)
        .toString();
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Generated(from = "PayInRequest", generator = "Immutables")
  @Deprecated
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json
      implements PayInRequest, Serializable {

    private static final long serialVersionUID = 202107072355659502L;
    String userId;
    String walletId;
    String currency;
    String token;
    BigDecimal amount;
    @JsonProperty("userId")
    public void setUserId(String userId) {
      this.userId = userId;
    }
    @JsonProperty("walletId")
    public void setWalletId(String walletId) {
      this.walletId = walletId;
    }
    @JsonProperty("currency")
    public void setCurrency(String currency) {
      this.currency = currency;
    }
    @JsonProperty("token")
    public void setToken(String token) {
      this.token = token;
    }
    @JsonProperty("amount")
    public void setAmount(BigDecimal amount) {
      this.amount = amount;
    }
    @Override
    public String getUserId() { throw new UnsupportedOperationException(); }
    @Override
    public String getWalletId() { throw new UnsupportedOperationException(); }
    @Override
    public String getCurrency() { throw new UnsupportedOperationException(); }
    @Override
    public String getToken() { throw new UnsupportedOperationException(); }
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
  static ImmutablePayInRequest fromJson(Json json) {
    ImmutablePayInRequest.Builder builder = ImmutablePayInRequest.builder();
    if (json.userId != null) {
      builder.userId(json.userId);
    }
    if (json.walletId != null) {
      builder.walletId(json.walletId);
    }
    if (json.currency != null) {
      builder.currency(json.currency);
    }
    if (json.token != null) {
      builder.token(json.token);
    }
    if (json.amount != null) {
      builder.amount(json.amount);
    }
    return builder.build();
  }

  /**
   * Creates an immutable copy of a {@link PayInRequest} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable PayInRequest instance
   */
  public static ImmutablePayInRequest copyOf(PayInRequest instance) {
    if (instance instanceof ImmutablePayInRequest) {
      return (ImmutablePayInRequest) instance;
    }
    return ImmutablePayInRequest.builder()
        .from(instance)
        .build();
  }

  private static final long serialVersionUID = 202107072355659502L;

  /**
   * Creates a builder for {@link ImmutablePayInRequest ImmutablePayInRequest}.
   * <pre>
   * ImmutablePayInRequest.builder()
   *    .userId(String) // required {@link PayInRequest#getUserId() userId}
   *    .walletId(String) // required {@link PayInRequest#getWalletId() walletId}
   *    .currency(String) // required {@link PayInRequest#getCurrency() currency}
   *    .token(String) // required {@link PayInRequest#getToken() token}
   *    .amount(java.math.BigDecimal) // required {@link PayInRequest#getAmount() amount}
   *    .build();
   * </pre>
   * @return A new ImmutablePayInRequest builder
   */
  public static ImmutablePayInRequest.Builder builder() {
    return new ImmutablePayInRequest.Builder();
  }

  /**
   * Builds instances of type {@link ImmutablePayInRequest ImmutablePayInRequest}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "PayInRequest", generator = "Immutables")
  public static final class Builder {
    private static final long INIT_BIT_USER_ID = 0x1L;
    private static final long INIT_BIT_WALLET_ID = 0x2L;
    private static final long INIT_BIT_CURRENCY = 0x4L;
    private static final long INIT_BIT_TOKEN = 0x8L;
    private static final long INIT_BIT_AMOUNT = 0x10L;
    private long initBits = 0x1fL;

    private String userId;
    private String walletId;
    private String currency;
    private String token;
    private BigDecimal amount;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code PayInRequest} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(PayInRequest instance) {
      Objects.requireNonNull(instance, "instance");
      userId(instance.getUserId());
      walletId(instance.getWalletId());
      currency(instance.getCurrency());
      token(instance.getToken());
      amount(instance.getAmount());
      return this;
    }

    /**
     * Initializes the value for the {@link PayInRequest#getUserId() userId} attribute.
     * @param userId The value for userId 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("userId")
    public final Builder userId(String userId) {
      this.userId = Objects.requireNonNull(userId, "userId");
      initBits &= ~INIT_BIT_USER_ID;
      return this;
    }

    /**
     * Initializes the value for the {@link PayInRequest#getWalletId() walletId} attribute.
     * @param walletId The value for walletId 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("walletId")
    public final Builder walletId(String walletId) {
      this.walletId = Objects.requireNonNull(walletId, "walletId");
      initBits &= ~INIT_BIT_WALLET_ID;
      return this;
    }

    /**
     * Initializes the value for the {@link PayInRequest#getCurrency() currency} attribute.
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
     * Initializes the value for the {@link PayInRequest#getToken() token} attribute.
     * @param token The value for token 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("token")
    public final Builder token(String token) {
      this.token = Objects.requireNonNull(token, "token");
      initBits &= ~INIT_BIT_TOKEN;
      return this;
    }

    /**
     * Initializes the value for the {@link PayInRequest#getAmount() amount} attribute.
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
     * Builds a new {@link ImmutablePayInRequest ImmutablePayInRequest}.
     * @return An immutable instance of PayInRequest
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutablePayInRequest build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutablePayInRequest(userId, walletId, currency, token, amount);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_USER_ID) != 0) attributes.add("userId");
      if ((initBits & INIT_BIT_WALLET_ID) != 0) attributes.add("walletId");
      if ((initBits & INIT_BIT_CURRENCY) != 0) attributes.add("currency");
      if ((initBits & INIT_BIT_TOKEN) != 0) attributes.add("token");
      if ((initBits & INIT_BIT_AMOUNT) != 0) attributes.add("amount");
      return "Cannot build PayInRequest, some of required attributes are not set " + attributes;
    }
  }
}
