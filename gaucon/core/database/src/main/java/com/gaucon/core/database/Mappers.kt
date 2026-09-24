package com.gaucon.core.database

import com.gaucon.core.database.entity.ActivityEntity
import com.gaucon.core.database.entity.ActivityLogEntity
import com.gaucon.core.database.entity.ChildEntity
import com.gaucon.core.database.entity.DailyPickEntity
import com.gaucon.core.database.entity.ReminderEntity
import com.gaucon.core.database.entity.ReminderLogEntity
import com.gaucon.domain.model.Activity
import com.gaucon.domain.model.ActivityLog
import com.gaucon.domain.model.Child
import com.gaucon.domain.model.ContentStatus
import com.gaucon.domain.model.DailyPick
import com.gaucon.domain.model.Domain
import com.gaucon.domain.model.Feedback
import com.gaucon.domain.model.Reminder
import com.gaucon.domain.model.ReminderAction
import com.gaucon.domain.model.ReminderType
import org.json.JSONArray
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime

internal fun stringListToJson(list: List<String>): String =
    JSONArray(list).toString()

internal fun jsonToStringList(json: String): List<String> {
    if (json.isBlank()) return emptyList()
    val arr = JSONArray(json)
    return buildList {
        for (i in 0 until arr.length()) add(arr.getString(i))
    }
}

fun ChildEntity.toModel(): Child = Child(
    id = id,
    displayName = displayName,
    birthDate = LocalDate.parse(birthDate),
    gender = gender,
    photoUri = photoUri,
)

fun Child.toEntity(): ChildEntity = ChildEntity(
    id = id,
    displayName = displayName,
    birthDate = birthDate.toString(),
    gender = gender,
    photoUri = photoUri,
)

fun ActivityEntity.toModel(): Activity = Activity(
    id = id,
    ageMinMonths = ageMinMonths,
    ageMaxMonths = ageMaxMonths,
    domains = jsonToStringList(domainsJson).mapNotNull { runCatching { Domain.valueOf(it) }.getOrNull() },
    title = title,
    goal = goal,
    materials = jsonToStringList(materialsJson),
    steps = jsonToStringList(stepsJson),
    safety = safety,
    easier = easier,
    harder = harder,
    durationMinutes = durationMinutes,
    isRetired = isRetired,
    contentVersion = contentVersion,
    reviewedBy = jsonToStringList(reviewedByJson),
    contentStatus = runCatching { ContentStatus.valueOf(contentStatus) }.getOrDefault(ContentStatus.DRAFT_UNREVIEWED),
    parentPhrases = jsonToStringList(parentPhrasesJson),
)

fun Activity.toEntity(): ActivityEntity = ActivityEntity(
    id = id,
    ageMinMonths = ageMinMonths,
    ageMaxMonths = ageMaxMonths,
    domainsJson = stringListToJson(domains.map { it.name }),
    title = title,
    goal = goal,
    materialsJson = stringListToJson(materials),
    stepsJson = stringListToJson(steps),
    safety = safety,
    easier = easier,
    harder = harder,
    durationMinutes = durationMinutes,
    isRetired = isRetired,
    contentVersion = contentVersion,
    reviewedByJson = stringListToJson(reviewedBy),
    contentStatus = contentStatus.name,
    parentPhrasesJson = stringListToJson(parentPhrases),
)

fun ActivityLogEntity.toModel(): ActivityLog = ActivityLog(
    id = id,
    childId = childId,
    activityId = activityId,
    completedAt = Instant.ofEpochMilli(completedAt),
    feedback = feedback?.let { runCatching { Feedback.valueOf(it) }.getOrNull() },
)

fun ActivityLog.toEntity(): ActivityLogEntity = ActivityLogEntity(
    id = id,
    childId = childId,
    activityId = activityId,
    completedAt = completedAt.toEpochMilli(),
    feedback = feedback?.name,
)

fun DailyPickEntity.toModel(): DailyPick = DailyPick(
    childId = childId,
    dateIso = dateIso,
    activityIds = jsonToStringList(activityIdsJson),
    generatedAt = Instant.ofEpochMilli(generatedAt),
)

fun DailyPick.toEntity(): DailyPickEntity = DailyPickEntity(
    childId = childId,
    dateIso = dateIso,
    activityIdsJson = stringListToJson(activityIds),
    generatedAt = generatedAt.toEpochMilli(),
)

fun ReminderEntity.toModel(): Reminder = Reminder(
    id = id,
    childId = childId,
    type = runCatching { ReminderType.valueOf(type) }.getOrDefault(ReminderType.DAILY_ACTIVITY),
    enabled = enabled,
    time = LocalTime.parse(time),
    isUserDefined = isUserDefined,
)

fun Reminder.toEntity(): ReminderEntity = ReminderEntity(
    id = id,
    childId = childId,
    type = type.name,
    enabled = enabled,
    time = time.toString(),
    isUserDefined = isUserDefined,
)

fun ReminderLogEntity.toModel(): ReminderLog = ReminderLog(
    id = id,
    reminderId = reminderId,
    firedAt = Instant.ofEpochMilli(firedAt),
    action = runCatching { ReminderAction.valueOf(action) }.getOrDefault(ReminderAction.SHOWN),
)

fun ReminderLog.toEntity(): ReminderLogEntity = ReminderLogEntity(
    id = id,
    reminderId = reminderId,
    firedAt = firedAt.toEpochMilli(),
    action = action.name,
)
