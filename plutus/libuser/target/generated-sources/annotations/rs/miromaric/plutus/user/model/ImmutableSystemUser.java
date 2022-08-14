package rs.miromaric.plutus.user.model;

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
import rs.miromaric.plutus.common.model.HasUuid;

/**
 * Immutable implementation of {@link SystemUser}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutableSystemUser.builder()}.
 */
@Generated(from = "SystemUser", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
public final class ImmutableSystemUser
    implements SystemUser, Serializable {
  private final String uuid;
  private final String username;
  private final String firstname;
  private final String lastname;
  private final Boolean isActive;

  private ImmutableSystemUser(ImmutableSystemUser.Builder builder) {
    this.username = builder.username;
    this.firstname = builder.firstname;
    this.lastname = builder.lastname;
    if (builder.uuid != null) {
      initShim.uuid(builder.uuid);
    }
    if (builder.isActive != null) {
      initShim.isActive(builder.isActive);
    }
    this.uuid = initShim.getUuid();
    this.isActive = initShim.isActive();
    this.initShim = null;
  }

  private ImmutableSystemUser(
      String uuid,
      String username,
      String firstname,
      String lastname,
      Boolean isActive) {
    this.uuid = uuid;
    this.username = username;
    this.firstname = firstname;
    this.lastname = lastname;
    this.isActive = isActive;
    this.initShim = null;
  }

  private static final byte STAGE_INITIALIZING = -1;
  private static final byte STAGE_UNINITIALIZED = 0;
  private static final byte STAGE_INITIALIZED = 1;
  private transient volatile InitShim initShim = new InitShim();

  @Generated(from = "SystemUser", generator = "Immutables")
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
      if (isActiveBuildStage == STAGE_INITIALIZING) attributes.add("isActive");
      return "Cannot build SystemUser, attribute initializers form cycle " + attributes;
    }
  }

  private String getUuidInitialize() {
    return SystemUser.super.getUuid();
  }

  private Boolean isActiveInitialize() {
    return SystemUser.super.isActive();
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
   * @return The value of the {@code username} attribute
   */
  @JsonProperty("username")
  @Override
  public String getUsername() {
    return username;
  }

  /**
   * @return The value of the {@code firstname} attribute
   */
  @JsonProperty("firstname")
  @Override
  public String getFirstname() {
    return firstname;
  }

  /**
   * @return The value of the {@code lastname} attribute
   */
  @JsonProperty("lastname")
  @Override
  public String getLastname() {
    return lastname;
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
   * Copy the current immutable object by setting a value for the {@link SystemUser#getUuid() uuid} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for uuid
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableSystemUser withUuid(String value) {
    String newValue = Objects.requireNonNull(value, "uuid");
    if (this.uuid.equals(newValue)) return this;
    return new ImmutableSystemUser(newValue, this.username, this.firstname, this.lastname, this.isActive);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link SystemUser#getUsername() username} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for username
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableSystemUser withUsername(String value) {
    String newValue = Objects.requireNonNull(value, "username");
    if (this.username.equals(newValue)) return this;
    return new ImmutableSystemUser(this.uuid, newValue, this.firstname, this.lastname, this.isActive);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link SystemUser#getFirstname() firstname} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for firstname
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableSystemUser withFirstname(String value) {
    String newValue = Objects.requireNonNull(value, "firstname");
    if (this.firstname.equals(newValue)) return this;
    return new ImmutableSystemUser(this.uuid, this.username, newValue, this.lastname, this.isActive);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link SystemUser#getLastname() lastname} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for lastname
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableSystemUser withLastname(String value) {
    String newValue = Objects.requireNonNull(value, "lastname");
    if (this.lastname.equals(newValue)) return this;
    return new ImmutableSystemUser(this.uuid, this.username, this.firstname, newValue, this.isActive);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link SystemUser#isActive() isActive} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for isActive
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableSystemUser withIsActive(Boolean value) {
    Boolean newValue = Objects.requireNonNull(value, "isActive");
    if (this.isActive.equals(newValue)) return this;
    return new ImmutableSystemUser(this.uuid, this.username, this.firstname, this.lastname, newValue);
  }

  /**
   * This instance is equal to all instances of {@code ImmutableSystemUser} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableSystemUser
        && equalTo((ImmutableSystemUser) another);
  }

  private boolean equalTo(ImmutableSystemUser another) {
    return uuid.equals(another.uuid)
        && username.equals(another.username)
        && firstname.equals(another.firstname)
        && lastname.equals(another.lastname)
        && isActive.equals(another.isActive);
  }

  /**
   * Computes a hash code from attributes: {@code uuid}, {@code username}, {@code firstname}, {@code lastname}, {@code isActive}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + uuid.hashCode();
    h += (h << 5) + username.hashCode();
    h += (h << 5) + firstname.hashCode();
    h += (h << 5) + lastname.hashCode();
    h += (h << 5) + isActive.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code SystemUser} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("SystemUser")
        .omitNullValues()
        .add("uuid", uuid)
        .add("username", username)
        .add("firstname", firstname)
        .add("lastname", lastname)
        .add("isActive", isActive)
        .toString();
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Generated(from = "SystemUser", generator = "Immutables")
  @Deprecated
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json implements SystemUser, Serializable {

    private static final long serialVersionUID = 20210704175300256L;
    String uuid;
    String username;
    String firstname;
    String lastname;
    Boolean isActive;
    @JsonProperty("uuid")
    public void setUuid(String uuid) {
      this.uuid = uuid;
    }
    @JsonProperty("username")
    public void setUsername(String username) {
      this.username = username;
    }
    @JsonProperty("firstname")
    public void setFirstname(String firstname) {
      this.firstname = firstname;
    }
    @JsonProperty("lastname")
    public void setLastname(String lastname) {
      this.lastname = lastname;
    }
    @JsonProperty("isActive")
    public void setIsActive(Boolean isActive) {
      this.isActive = isActive;
    }
    @Override
    public String getUuid() { throw new UnsupportedOperationException(); }
    @Override
    public String getUsername() { throw new UnsupportedOperationException(); }
    @Override
    public String getFirstname() { throw new UnsupportedOperationException(); }
    @Override
    public String getLastname() { throw new UnsupportedOperationException(); }
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
  static ImmutableSystemUser fromJson(Json json) {
    ImmutableSystemUser.Builder builder = ImmutableSystemUser.builder();
    if (json.uuid != null) {
      builder.uuid(json.uuid);
    }
    if (json.username != null) {
      builder.username(json.username);
    }
    if (json.firstname != null) {
      builder.firstname(json.firstname);
    }
    if (json.lastname != null) {
      builder.lastname(json.lastname);
    }
    if (json.isActive != null) {
      builder.isActive(json.isActive);
    }
    return builder.build();
  }

  /**
   * Creates an immutable copy of a {@link SystemUser} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable SystemUser instance
   */
  public static ImmutableSystemUser copyOf(SystemUser instance) {
    if (instance instanceof ImmutableSystemUser) {
      return (ImmutableSystemUser) instance;
    }
    return ImmutableSystemUser.builder()
        .from(instance)
        .build();
  }

  private static final long serialVersionUID = 20210704175300256L;

  /**
   * Creates a builder for {@link ImmutableSystemUser ImmutableSystemUser}.
   * <pre>
   * ImmutableSystemUser.builder()
   *    .uuid(String) // optional {@link SystemUser#getUuid() uuid}
   *    .username(String) // required {@link SystemUser#getUsername() username}
   *    .firstname(String) // required {@link SystemUser#getFirstname() firstname}
   *    .lastname(String) // required {@link SystemUser#getLastname() lastname}
   *    .isActive(Boolean) // optional {@link SystemUser#isActive() isActive}
   *    .build();
   * </pre>
   * @return A new ImmutableSystemUser builder
   */
  public static ImmutableSystemUser.Builder builder() {
    return new ImmutableSystemUser.Builder();
  }

  /**
   * Builds instances of type {@link ImmutableSystemUser ImmutableSystemUser}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "SystemUser", generator = "Immutables")
  public static final class Builder {
    private static final long INIT_BIT_USERNAME = 0x1L;
    private static final long INIT_BIT_FIRSTNAME = 0x2L;
    private static final long INIT_BIT_LASTNAME = 0x4L;
    private long initBits = 0x7L;

    private String uuid;
    private String username;
    private String firstname;
    private String lastname;
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
     * Fill a builder with attribute values from the provided {@code rs.miromaric.plutus.user.model.SystemUser} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(SystemUser instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return this;
    }

    private void from(Object object) {
      if (object instanceof HasUuid) {
        HasUuid instance = (HasUuid) object;
        uuid(instance.getUuid());
      }
      if (object instanceof SystemUser) {
        SystemUser instance = (SystemUser) object;
        firstname(instance.getFirstname());
        isActive(instance.isActive());
        username(instance.getUsername());
        lastname(instance.getLastname());
      }
    }

    /**
     * Initializes the value for the {@link SystemUser#getUuid() uuid} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link SystemUser#getUuid() uuid}.</em>
     * @param uuid The value for uuid 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("uuid")
    public final Builder uuid(String uuid) {
      this.uuid = Objects.requireNonNull(uuid, "uuid");
      return this;
    }

    /**
     * Initializes the value for the {@link SystemUser#getUsername() username} attribute.
     * @param username The value for username 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("username")
    public final Builder username(String username) {
      this.username = Objects.requireNonNull(username, "username");
      initBits &= ~INIT_BIT_USERNAME;
      return this;
    }

    /**
     * Initializes the value for the {@link SystemUser#getFirstname() firstname} attribute.
     * @param firstname The value for firstname 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("firstname")
    public final Builder firstname(String firstname) {
      this.firstname = Objects.requireNonNull(firstname, "firstname");
      initBits &= ~INIT_BIT_FIRSTNAME;
      return this;
    }

    /**
     * Initializes the value for the {@link SystemUser#getLastname() lastname} attribute.
     * @param lastname The value for lastname 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("lastname")
    public final Builder lastname(String lastname) {
      this.lastname = Objects.requireNonNull(lastname, "lastname");
      initBits &= ~INIT_BIT_LASTNAME;
      return this;
    }

    /**
     * Initializes the value for the {@link SystemUser#isActive() isActive} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link SystemUser#isActive() isActive}.</em>
     * @param isActive The value for isActive 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("isActive")
    public final Builder isActive(Boolean isActive) {
      this.isActive = Objects.requireNonNull(isActive, "isActive");
      return this;
    }

    /**
     * Builds a new {@link ImmutableSystemUser ImmutableSystemUser}.
     * @return An immutable instance of SystemUser
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutableSystemUser build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableSystemUser(this);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_USERNAME) != 0) attributes.add("username");
      if ((initBits & INIT_BIT_FIRSTNAME) != 0) attributes.add("firstname");
      if ((initBits & INIT_BIT_LASTNAME) != 0) attributes.add("lastname");
      return "Cannot build SystemUser, some of required attributes are not set " + attributes;
    }
  }
}
